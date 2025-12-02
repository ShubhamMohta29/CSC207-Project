package tests;

import classes.addFavorite.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddFavoriteInteractorTest {

    String name = "Cheetah";

    @Test
    public void successAdd() {
        addFavoriteInputData inputData = new addFavoriteInputData(name);
        addFavoriteDataAccessInterface favList = new inMemoryFavoritesDataAccessObject();
        addFavoriteInputBoundary interactor = new addFavoriteInteractor(favList);
        interactor.execute(inputData);
        assertTrue(favList.isFavorite(name));
    }
    @Test
    public void successRemove() {
        addFavoriteInputData inputData = new addFavoriteInputData(name);
        addFavoriteDataAccessInterface favList = new inMemoryFavoritesDataAccessObject();
        addFavoriteInputBoundary interactor = new addFavoriteInteractor(favList);
        interactor.execute1(inputData);
        assertFalse(favList.isFavorite(name));
    }}
