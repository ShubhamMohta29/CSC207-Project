package Classes.Compatibility.usecases;

/**
 * Output boundary for animal comparison use case.
 * Defines methods for presenting comparison results to the user.
 */
public interface CompareAnimalsOutputBoundary {
    /**
     * Present successful comparison results.
     *
     * @param animal1Name the name of the first animal
     * @param animal2Name the name of the second animal
     * @param matching the matching categories
     * @param conflicting the conflicting categories
     * @param rating the compatibility rating
     */
    void presentSuccess(String animal1Name, String animal2Name,
                        String matching, String conflicting, String rating);

    /**
     * Present error when first animal is invalid.
     */
    void presentAnimal1Error();

    /**
     * Present error when second animal is invalid.
     */
    void presentAnimal2Error();

    /**
     * Present error when first animal is empty.
     */
    void presentAnimal1Empty();

    /**
     * Present error when second animal is empty.
     */
    void presentAnimal2Empty();
}