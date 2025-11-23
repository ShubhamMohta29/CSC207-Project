package Tests;

import Classes.add_favorite.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddFavoriteInteractorTest {

    @Test
    public void successAdd() {
        AddFavoriteInputData inputData = new AddFavoriteInputData("Cheetah");
        AddFavoriteDataAccessInterface favList = new InMemoryFavoritesDataAccessObject();
        AddFavoriteInputBoundary interactor = new AddFavoriteInteractor(favList);
        interactor.execute(inputData);
        assertTrue(favList.isFavorite("Cheetah"));
    }
    @Test
    public void successRemove() {
        AddFavoriteInputData inputData = new AddFavoriteInputData("Cheetah");
        AddFavoriteDataAccessInterface favList = new InMemoryFavoritesDataAccessObject();
        AddFavoriteInputBoundary interactor = new AddFavoriteInteractor(favList);
        interactor.execute1(inputData);
        assertFalse(favList.isFavorite("Cheetah"));
    }}
