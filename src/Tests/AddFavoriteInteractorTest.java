package Tests;

import classes.add_favorite.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddFavoriteInteractorTest {

    String name = "Cheetah";

    @Test
    public void successAdd() {
        AddFavoriteInputData inputData = new AddFavoriteInputData(name);
        AddFavoriteDataAccessInterface favList = new InMemoryFavoritesDataAccessObject();
        AddFavoriteInputBoundary interactor = new AddFavoriteInteractor(favList);
        interactor.execute(inputData);
        assertTrue(favList.isFavorite(name));
    }
    @Test
    public void successRemove() {
        AddFavoriteInputData inputData = new AddFavoriteInputData(name);
        AddFavoriteDataAccessInterface favList = new InMemoryFavoritesDataAccessObject();
        AddFavoriteInputBoundary interactor = new AddFavoriteInteractor(favList);
        interactor.execute1(inputData);
        assertFalse(favList.isFavorite(name));
    }}
