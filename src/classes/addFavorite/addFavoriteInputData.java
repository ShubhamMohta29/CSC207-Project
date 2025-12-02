package classes.addFavorite;

/**
 * The input data for the add favorite use case.
 */
public class addFavoriteInputData {
    private final String name;

    public addFavoriteInputData(String name){
        this.name = name;
    }

    public String getName() {return name;}
}
