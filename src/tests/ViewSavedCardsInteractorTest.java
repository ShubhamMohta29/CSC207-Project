package tests;

import classes.viewSavedCards.*;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadSavedCardsInteractorTest {

    private static class MockDataAccess implements loadSavedCardsDataAccessInterface {
        List<String> names;
        List<BufferedImage> images;

        @Override
        public List<String> loadAllCardNames() { return names; }

        @Override
        public List<BufferedImage> loadAllCardImages() { return images; }
    }

    private static class MockPresenter implements loadSavedCardsOutputBoundary {
        loadSavedCardsResponseModel received;

        @Override
        public loadSavedCardsResponseModel prepareSuccessView(loadSavedCardsResponseModel responseModel) {
            received = responseModel;
            return responseModel;
        }
    }

    @Test
    void loadsCardsSuccessfully() {
        MockDataAccess data = new MockDataAccess();
        data.names = List.of("LionCard", "TigerCard");
        data.images = List.of(
                new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB),
                new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB)
        );

        MockPresenter presenter = new MockPresenter();
        loadSavedCardsInteractor interactor = new loadSavedCardsInteractor(data, presenter);

        loadSavedCardsResponseModel result = interactor.load(new loadSavedCardsRequestModel());

        assertNotNull(result);
        assertEquals(2, presenter.received.getCardNames().size());
        assertEquals(2, presenter.received.getCardImages().size());
        assertEquals("LionCard", presenter.received.getCardNames().get(0));
    }

    @Test
    void loadsEmptyLibraryCorrectly() {
        MockDataAccess data = new MockDataAccess();
        data.names = List.of();
        data.images = List.of();

        MockPresenter presenter = new MockPresenter();
        loadSavedCardsInteractor interactor = new loadSavedCardsInteractor(data, presenter);

        loadSavedCardsResponseModel result = interactor.load(new loadSavedCardsRequestModel());

        assertNotNull(result);
        assertTrue(presenter.received.getCardNames().isEmpty());
        assertTrue(presenter.received.getCardImages().isEmpty());
    }

    @Test
    void presenterReceivesExactResponse() {
        MockDataAccess data = new MockDataAccess();
        BufferedImage img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        data.names = List.of("ElephantCard");
        data.images = List.of(img);

        MockPresenter presenter = new MockPresenter();
        loadSavedCardsInteractor interactor = new loadSavedCardsInteractor(data, presenter);

        interactor.load(new loadSavedCardsRequestModel());

        assertEquals("ElephantCard", presenter.received.getCardNames().get(0));
        assertSame(img, presenter.received.getCardImages().get(0));
    }
}