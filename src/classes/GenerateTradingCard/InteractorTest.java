package classes.GenerateTradingCard;

import classes.retrieveInfo.Animal;
import classes.retrieveInfo.AnimalFactory;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GenerateTradingCardInteractor.
 */
class GenerateTradingCardInteractorTest {

    /**
     * JSON for Animal constructor.
     */
    private static final String ANIMAL_JSON = """
    [
      {
        "name": "Fox",
        "taxonomy": {"kingdom":"Animalia"},
        "locations": ["Forest"],
        "characteristics": {
          "habitat":"Forest",
          "prey":"Rabbits",
          "most_distinctive_feature":"Bushy tail",
          "lifespan":"5 years",
          "diet":"Omnivore",
          "lifestyle":"Solitary",
          "weight":"6kg",
          "height":"40cm",
          "group":"Mammal",
          "type":"Animal",
          "slogan":"Sly and quick"
        }
      }
    ]
    """;

    /**
     * Mock generator.
     */
    private static class MockGenerator implements CardImageGenerator {
        @Override
        public BufferedImage generate(Animal a) {
            return new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        }
    }

    /**
     * Mock presenter.
     */
    private static class MockPresenter implements GenerateTradingCardOutputBoundary {
        TradingCardViewModel received;

        @Override
        public TradingCardViewModel prepareSuccessView(GenerateTradingCardResponseModel response) {
            TradingCardViewModel vm = new TradingCardViewModel();
            vm.setAnimalName(response.getAnimal().getName());
            vm.setImage(response.getImage());
            received = vm;
            return vm;
        }
    }

    @Test
    void interactorGeneratesValidViewModel() {

        MockPresenter presenter = new MockPresenter();
        MockGenerator generator = new MockGenerator();

        GenerateTradingCardInteractor interactor =
                new GenerateTradingCardInteractor(presenter, generator);

        Animal fox = new AnimalFactory().fromJsonArrayString(ANIMAL_JSON);

        TradingCardViewModel result =
                interactor.generate(new GenerateTradingCardRequestModel(fox));

        assertNotNull(result);
        assertEquals("Fox", result.getAnimalName());
        assertNotNull(result.getImage());

        assertNotNull(presenter.received);
        assertEquals("Fox", presenter.received.getAnimalName());
        assertNotNull(presenter.received.getImage());
    }
}
