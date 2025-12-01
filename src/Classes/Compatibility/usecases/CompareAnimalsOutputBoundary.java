package Classes.Compatibility.usecases;

public interface
CompareAnimalsOutputBoundary {
    void presentSuccess(String animal1Name, String animal2Name,
                        String matching, String conflicting, String rating);
    void presentAnimal1Error();
    void presentAnimal2Error();
    void presentAnimal1Empty();
    void presentAnimal2Empty();
}