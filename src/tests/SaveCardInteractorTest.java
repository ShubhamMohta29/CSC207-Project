package Tests;

import Classes.SaveCard.*;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SaveCardInteractorTest {

    private static class MockDataAccess implements SaveCardDataAccessInterface {
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

    private static class MockPresenter implements SaveCardOutputBoundary {
        SaveCardResponseModel lastSuccess;
        String lastError;

        @Override
        public SaveCardResponseModel prepareSuccessView(SaveCardResponseModel response) {
            lastSuccess = response;
            return response;
        }

        @Override
        public SaveCardResponseModel prepareFailView(String error) {
            lastError = error;
            return null;
        }
    }

    @Test
    void savesSuccessfullyWhenNotExisting() {
        MockDataAccess data = new MockDataAccess();
        MockPresenter presenter = new MockPresenter();

        SaveCardInteractor interactor = new SaveCardInteractor(data, presenter);

        SaveCardRequestModel request = new SaveCardRequestModel("LionCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));

        SaveCardResponseModel result = interactor.save(request);

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
        SaveCardInteractor interactor = new SaveCardInteractor(data, presenter);

        SaveCardRequestModel request = new SaveCardRequestModel("TigerCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        SaveCardResponseModel result = interactor.save(request);

        assertNull(result);
        assertEquals("Card name already exists.", presenter.lastError);
        assertFalse(data.saveCalled);
    }

    @Test
    void handlesDataAccessFailureGracefully() {
        MockDataAccess data = new MockDataAccess();
        data.throwError = true;

        MockPresenter presenter = new MockPresenter();
        SaveCardInteractor interactor = new SaveCardInteractor(data, presenter);

        SaveCardRequestModel request = new SaveCardRequestModel("BearCard", new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        SaveCardResponseModel result = interactor.save(request);

        assertNull(result);
        assertTrue(presenter.lastError.startsWith("Failed to save card:"));
    }
}
