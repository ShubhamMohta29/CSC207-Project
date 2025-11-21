package Classes.Compatibility.usecases;

import Classes.Animal;
import java.util.HashSet;
import java.util.Set;

public class CompareAnimalsInteractor implements CompareAnimalsInputBoundary {
    private static final String[] EXPECTED_CATEGORIES = {
            "Group", "Diet", "Lifestyle", "Location", "Prey", "Habitat", "Lifespan", "Height", "Weight"
    };

    private final AnimalDataAccessInterface dataAccess;
    private final CompareAnimalsOutputBoundary presenter;

    public CompareAnimalsInteractor(AnimalDataAccessInterface dataAccess,
                                    CompareAnimalsOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(String animal1Name, String animal2Name) {
        // Fetch animals
        Animal animal1 = dataAccess.getAnimalByName(animal1Name);
        if (animal1 == null) {
            presenter.presentAnimal1Error();
            return;
        }

        Animal animal2 = dataAccess.getAnimalByName(animal2Name);
        if (animal2 == null) {
            presenter.presentAnimal2Error();
            return;
        }

        // Calculate compatibility
        Set<String> similar = calculateSimilarCategories(animal1, animal2);
        Set<String> conflicting = calculateConflictingCategories(similar);
        int rating = calculateRating(similar.size());

        // Format and present results
        String matchingStr = String.join(", ", similar);
        String conflictingStr = String.join(", ", conflicting);
        String ratingStr = "Rating: " + rating + "%";

        presenter.presentSuccess(animal1.getName(), animal2.getName(),
                matchingStr, conflictingStr, ratingStr);
    }

    private Set<String> calculateSimilarCategories(Animal animal1, Animal animal2) {
        Set<String> similar = new HashSet<>();

        if (animal1.getGroup().equals(animal2.getGroup()) ||
                animal1.getGroup().isEmpty() || animal2.getGroup().isEmpty()) {
            similar.add("Group");
        }

        if (animal1.getDiet().equals(animal2.getDiet()) ||
                animal1.getDiet().isEmpty() || animal2.getDiet().isEmpty()) {
            similar.add("Diet");
        }

        if (animal1.getLifestyle().equals(animal2.getLifestyle()) ||
                animal1.getLifestyle().isEmpty() || animal2.getLifestyle().isEmpty()) {
            similar.add("Lifestyle");
        }

        if (hasLocationOverlap(animal1.getLocation(), animal2.getLocation())) {
            similar.add("Location");
        }

        if (hasPreyOverlap(animal1.getPrey(), animal2.getPrey())) {
            similar.add("Prey");
        }

        if (animal1.getHabitat().equals(animal2.getHabitat()) ||
                animal1.getHabitat().isEmpty() || animal2.getHabitat().isEmpty()) {
            similar.add("Habitat");
        }

        if (relativeDiff(animal1.getLifespan(), animal2.getLifespan()) < 0.4) {
            similar.add("Lifespan");
        }

        if (relativeDiff(animal1.getHeight(), animal2.getHeight()) < 0.4) {
            similar.add("Height");
        }

        if (relativeDiff(animal1.getWeight(), animal2.getWeight()) < 0.4) {
            similar.add("Weight");
        }

        return similar;
    }

    private Set<String> calculateConflictingCategories(Set<String> similar) {
        Set<String> conflicting = new HashSet<>();
        for (String category : EXPECTED_CATEGORIES) {
            if (!similar.contains(category)) {
                conflicting.add(category);
            }
        }
        return conflicting;
    }

    private int calculateRating(int matchingCount) {
        return (int) ((((double) matchingCount) / EXPECTED_CATEGORIES.length) * 100);
    }

    private boolean hasLocationOverlap(String[] location1, String[] location2) {
        if (location1.length == 0 || location2.length == 0) return true;
        if (location1[0].equals("Worldwide") || location2[0].equals("Worldwide")) return true;

        for (String loc1 : location1) {
            for (String loc2 : location2) {
                if (loc1.equals(loc2)) return true;
            }
        }
        return false;
    }

    private boolean hasPreyOverlap(String[] prey1, String[] prey2) {
        for (String p1 : prey1) {
            for (String p2 : prey2) {
                if (p1.equals(p2)) return true;
            }
        }
        return false;
    }

    private double relativeDiff(double a, double b) {
        if (a == 0 && b == 0) return 0;
        return Math.abs(a - b) / ((a + b) / 2.0);
    }
}