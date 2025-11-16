package Classes.add_favorite;

/**
 * The input data for the add favorite use case.
 */
public class AddFavoriteInputData {
    private final String name;

    public AddFavoriteInputData(String name){
        this.name = name;
    }

    public String getName() {return name;}
}
