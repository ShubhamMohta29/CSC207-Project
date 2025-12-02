// ============================================
// FILE: CompareAnimalsInteractorTest.java
// PATH: test/Classes/Compatibility/usecases/CompareAnimalsInteractorTest.java
// Integration tests for animal comparison flows
// ============================================

package tests;

import classes.retrieveInfo.animal;
import classes.compatibility.usecases.*;
import classes.retrieveInfo.animalFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareAnimalsInteractorTest {

    @Test
    void testMainFlow_BothAnimalsValid() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        dataAccess.addAnimal("Lion", createLion());
        dataAccess.addAnimal("Tiger", createTiger());

        interactor.execute("Lion", "Tiger");

        assertTrue(presenter.wasSuccessCalled);
        assertEquals("Lion", presenter.animal1Name);
        assertEquals("Tiger", presenter.animal2Name);
        assertNotNull(presenter.matching);
        assertNotNull(presenter.conflicting);
        assertTrue(presenter.rating.contains("%"));
    }

    @Test
    void testMainFlow_BothAnimalsEmpty() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        interactor.execute("", "");

        assertTrue(presenter.wasAnimal1EmptyCalled);
        assertTrue(presenter.wasAnimal2EmptyCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    @Test
    void testMainFlow_FirstEmptySecondValid() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        dataAccess.addAnimal("Tiger", createTiger());

        interactor.execute("", "Tiger");

        assertTrue(presenter.wasAnimal1EmptyCalled);
        assertFalse(presenter.wasAnimal2EmptyCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    @Test
    void testMainFlow_FirstValidSecondEmpty() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        dataAccess.addAnimal("Lion", createLion());

        interactor.execute("Lion", "");

        assertTrue(presenter.wasAnimal2EmptyCalled);
        assertFalse(presenter.wasAnimal1EmptyCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    @Test
    void testMainFlow_BothAnimalsInvalid() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        interactor.execute("Unicorn", "Dragon");

        assertTrue(presenter.wasAnimal1ErrorCalled);
        assertTrue(presenter.wasAnimal2ErrorCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    @Test
    void testMainFlow_FirstInvalidSecondValid() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        dataAccess.addAnimal("Tiger", createTiger());

        interactor.execute("Unicorn", "Tiger");

        assertTrue(presenter.wasAnimal1ErrorCalled);
        assertFalse(presenter.wasAnimal2ErrorCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    @Test
    void testMainFlow_FirstValidSecondInvalid() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestPresenter presenter = new TestPresenter();
        compareAnimalsInteractor interactor = new compareAnimalsInteractor(dataAccess, presenter);

        dataAccess.addAnimal("Lion", createLion());

        interactor.execute("Lion", "Dragon");

        assertTrue(presenter.wasAnimal2ErrorCalled);
        assertFalse(presenter.wasAnimal1ErrorCalled);
        assertFalse(presenter.wasSuccessCalled);
    }

    private animal createLion() {
        String lionJson = """
            [{
                "name": "Lion",
                "taxonomy": {
                    "kingdom": "Animalia",
                    "phylum": "Chordata",
                    "class": "Mammalia",
                    "order": "Carnivora",
                    "family": "Felidae",
                    "genus": "Panthera",
                    "scientific_name": "Panthera leo"
                },
                "locations": ["Africa"],
                "characteristics": {
                    "prey": "Zebra, Antelope, Wildebeest",
                    "habitat": "Grassland",
                    "diet": "Carnivore",
                    "lifestyle": "Crepuscular",
                    "group": "Mammal",
                    "lifespan": "10-14 years",
                    "weight": "190kg",
                    "height": "120cm",
                    "most_distinctive_feature": "Mane on male"
                }
            }]
            """;
        return new animalFactory().fromJsonArrayString(lionJson);
    }

    private animal createTiger() {
        String tigerJson = """
            [{
                "name": "Tiger",
                "taxonomy": {
                    "kingdom": "Animalia",
                    "phylum": "Chordata",
                    "class": "Mammalia",
                    "order": "Carnivora",
                    "family": "Felidae",
                    "genus": "Panthera",
                    "scientific_name": "Panthera tigris"
                },
                "locations": ["Asia"],
                "characteristics": {
                    "prey": "Deer, Pig, Wild Boar",
                    "habitat": "Forest",
                    "diet": "Carnivore",
                    "lifestyle": "Solitary",
                    "group": "Mammal",
                    "lifespan": "10-15 years",
                    "weight": "200kg",
                    "height": "110cm",
                    "most_distinctive_feature": "Orange coat with black stripes"
                }
            }]
            """;
        return new animalFactory().fromJsonArrayString(tigerJson);
    }

    private class TestDataAccess implements animalDataAccessInterface {
        private java.util.Map<String, animal> animals = new java.util.HashMap<>();

        public void addAnimal(String name, animal animal) {
            animals.put(name, animal);
        }

        @Override
        public animal getAnimalByName(String name) {
            return animals.get(name);
        }
    }

    private class TestPresenter implements compareAnimalsOutputBoundary {
        public boolean wasSuccessCalled = false;
        public boolean wasAnimal1ErrorCalled = false;
        public boolean wasAnimal2ErrorCalled = false;
        public boolean wasAnimal1EmptyCalled = false;
        public boolean wasAnimal2EmptyCalled = false;

        public String animal1Name;
        public String animal2Name;
        public String matching;
        public String conflicting;
        public String rating;

        @Override
        public void presentSuccess(String animal1Name, String animal2Name,
                                   String matching, String conflicting, String rating) {
            this.wasSuccessCalled = true;
            this.animal1Name = animal1Name;
            this.animal2Name = animal2Name;
            this.matching = matching;
            this.conflicting = conflicting;
            this.rating = rating;
        }

        @Override
        public void presentAnimal1Error() {
            this.wasAnimal1ErrorCalled = true;
        }

        @Override
        public void presentAnimal2Error() {
            this.wasAnimal2ErrorCalled = true;
        }

        @Override
        public void presentAnimal1Empty() {
            this.wasAnimal1EmptyCalled = true;
        }

        @Override
        public void presentAnimal2Empty() {
            this.wasAnimal2EmptyCalled = true;
        }
    }
}