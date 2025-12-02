package tests;

import classes.saveCard.*;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SaveCardInteractorTest {

    private static class MockDataAccess implements saveCardDataAccessInterface {
        boolean exists = false;
        boolean saveCalled = false;
        boolean throwError = false;

        @Override
        public boolean cardExists(String name) {
            return exists;
        }

        @Override
        public void saveCard(String name, BufferedImage image) {
            if (throwError) throw new RuntimeException("Disk write failure");
            saveCalled = true;
        }
    }

    private static class MockPresenter implements saveCardOutputBoundary {
        saveCardResponseModel lastSuccess;
        String lastError;

        @Override
        public saveCardResponseModel prepareSuccessView(saveCardResponseModel response) {
            lastSuccess = response;
            return response;
        }

        @Override
        public saveCardResponseModel prepareFailView(String error) {
            lastError = error;
            return null;
        }
    }

    @Test
    void savesSuccessfullyWhenNotExisting() {
        MockDataAccess data = new MockDataAccess();
        MockPresenter presenter = new MockPresenter();

        saveCardInteractor interactor = new saveCardInteractor(data, presenter);

        saveCardRequestModel request = new saveCardRequestModel("LionCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));

        saveCardResponseModel result = interactor.save(request);

        assertNotNull(result);
        assertNull(presenter.lastError);
        assertTrue(data.saveCalled);
        assertEquals("LionCard", presenter.lastSuccess.getCardName());
    }

    @Test
    void failsIfCardAlreadyExists() {
        MockDataAccess data = new MockDataAccess();
        data.exists = true;

        MockPresenter presenter = new MockPresenter();
        saveCardInteractor interactor = new saveCardInteractor(data, presenter);

        saveCardRequestModel request = new saveCardRequestModel("TigerCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        saveCardResponseModel result = interactor.save(request);

        assertNull(result);
        assertEquals("Card name already exists.", presenter.lastError);
        assertFalse(data.saveCalled);
    }

    @Test
    void handlesDataAccessFailureGracefully() {
        MockDataAccess data = new MockDataAccess();
        data.throwError = true;

        MockPresenter presenter = new MockPresenter();
        saveCardInteractor interactor = new saveCardInteractor(data, presenter);

        saveCardRequestModel request = new saveCardRequestModel("BearCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        saveCardResponseModel result = interactor.save(request);

        assertNull(result);
        assertTrue(presenter.lastError.startsWith("Failed to save card:"));
    }
}
