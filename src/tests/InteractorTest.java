package tests;

import classes.generateTradingCard.*;
import classes.retrieveInfo.animal;
import classes.retrieveInfo.animalFactory;
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
    private static class MockGenerator implements cardImageGenerator {
        @Override
        public BufferedImage generate(animal a) {
            return new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        }
    }

    /**
     * Mock presenter.
     */
    private static class MockPresenter implements generateTradingCardOutputBoundary {
        tradingCardViewModel received;

        @Override
        public tradingCardViewModel prepareSuccessView(generateTradingCardResponseModel response) {
            tradingCardViewModel vm = new tradingCardViewModel();
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

        generateTradingCardInteractor interactor =
                new generateTradingCardInteractor(presenter, generator);

        animal fox = new animalFactory().fromJsonArrayString(ANIMAL_JSON);

        tradingCardViewModel result =
                interactor.generate(new generateTradingCardRequestModel(fox));

        assertNotNull(result);
        assertEquals("Fox", result.getAnimalName());
        assertNotNull(result.getImage());

        assertNotNull(presenter.received);
        assertEquals("Fox", presenter.received.getAnimalName());
        assertNotNull(presenter.received.getImage());
    }
}


