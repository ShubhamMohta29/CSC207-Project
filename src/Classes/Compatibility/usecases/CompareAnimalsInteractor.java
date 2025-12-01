package Classes.Compatibility.usecases;

import java.util.HashSet;
import java.util.Set;

import Classes.Animal;

/**
 * Interactor for comparing two animals and calculating compatibility.
 * Implements the business logic for animal comparison.
 */
public final class CompareAnimalsInteractor
        implements CompareAnimalsInputBoundary {
    /**
     * Array of all categories used for animal comparison.
     */
    private static final String[] EXPECTED_CATEGORIES = {
            "Group",
            "Diet",
            "Lifestyle",
            "Location",
            "Prey",
            "Habitat",
            "Lifespan",
            "Height",
            "Weight",
    };

    /**
     * Threshold for determining if numeric values are compatible.
     * Values with relative difference below this are considered matching.
     */
    private static final double COMPATIBILITY_THRESHOLD = 0.4;

    /**
     * Multiplier to convert decimal ratio to percentage.
     */
    private static final int PERCENTAGE_MULTIPLIER = 100;

    /**
     * Constant for dividing by 2 in average calculations.
     */
    private static final double HALF = 2.0;

    /**
     * Data access interface for retrieving animal information.
     */
    private final AnimalDataAccessInterface dataAccess;

    /**
     * Output boundary for presenting comparison results.
     */
    private final CompareAnimalsOutputBoundary presenter;

    /**
     * Constructs a CompareAnimalsInteractor.
     *
     * @param dataAccess the data access interface
     * @param presenter the output boundary for presenting results
     */
    public CompareAnimalsInteractor(
            final AnimalDataAccessInterface dataAccess,
            final CompareAnimalsOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(final String animal1Name,
                        final String animal2Name) {
        final boolean emptyHandled =
                handleEmptyInputs(animal1Name, animal2Name);
        if (!emptyHandled) {
            final Animal animal1 =
                    dataAccess.getAnimalByName(animal1Name);
            final Animal animal2 =
                    dataAccess.getAnimalByName(animal2Name);

            final boolean nullHandled =
                    handleNullAnimals(animal1, animal2);
            if (!nullHandled) {
                processComparison(animal1, animal2);
            }
        }
    }

    /**
     * Handles empty input validation.
     *
     * @param animal1Name first animal name
     * @param animal2Name second animal name
     * @return true if any input is empty
     */
    private boolean handleEmptyInputs(final String animal1Name,
                                      final String animal2Name) {
        final boolean bothEmpty =
                "".equals(animal1Name) && "".equals(animal2Name);
        final boolean firstEmpty = "".equals(animal1Name);
        final boolean secondEmpty = "".equals(animal2Name);

        final boolean result;
        if (bothEmpty) {
            presenter.presentAnimal1Empty();
            presenter.presentAnimal2Empty();
            result = true;
        }
        else if (firstEmpty) {
            presenter.presentAnimal1Empty();
            result = true;
        }
        else if (secondEmpty) {
            presenter.presentAnimal2Empty();
            result = true;
        }
        else {
            result = false;
        }

        return result;
    }

    /**
     * Handles null animal validation.
     *
     * @param animal1 first animal object
     * @param animal2 second animal object
     * @return true if any animal is null
     */
    private boolean handleNullAnimals(final Animal animal1,
                                      final Animal animal2) {
        final boolean bothNull = animal1 == null && animal2 == null;
        final boolean firstNull = animal1 == null;
        final boolean secondNull = animal2 == null;

        final boolean result;
        if (bothNull) {
            presenter.presentAnimal1Error();
            presenter.presentAnimal2Error();
            result = true;
        }
        else if (firstNull) {
            presenter.presentAnimal1Error();
            result = true;
        }
        else if (secondNull) {
            presenter.presentAnimal2Error();
            result = true;
        }
        else {
            result = false;
        }

        return result;
    }

    /**
     * Processes the comparison between two animals.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     */
    private void processComparison(final Animal animal1,
                                   final Animal animal2) {
        final Set<String> similar =
                calculateSimilarCategories(animal1, animal2);
        final Set<String> conflicting =
                calculateConflictingCategories(similar);
        final int rating = calculateRating(similar.size());

        final String matchingStr = String.join(", ", similar);
        final String conflictingStr = String.join(", ", conflicting);
        final String ratingStr = "Rating: " + rating + "%";

        presenter.presentSuccess(animal1.getName(), animal2.getName(),
                matchingStr, conflictingStr, ratingStr);
    }

    /**
     * Calculates similar categories between two animals.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @return set of similar category names
     */
    private Set<String> calculateSimilarCategories(
            final Animal animal1, final Animal animal2) {
        final Set<String> similar = new HashSet<>();

        addIfGroupMatches(animal1, animal2, similar);
        addIfDietMatches(animal1, animal2, similar);
        addIfLifestyleMatches(animal1, animal2, similar);
        addIfLocationMatches(animal1, animal2, similar);
        addIfPreyMatches(animal1, animal2, similar);
        addIfHabitatMatches(animal1, animal2, similar);
        addIfLifespanMatches(animal1, animal2, similar);
        addIfHeightMatches(animal1, animal2, similar);
        addIfWeightMatches(animal1, animal2, similar);

        return similar;
    }

    /**
     * Adds Group category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfGroupMatches(final Animal animal1,
                                   final Animal animal2,
                                   final Set<String> similar) {
        if (animal1.getGroup().equals(animal2.getGroup())
                || animal1.getGroup().isEmpty()
                || animal2.getGroup().isEmpty()) {
            similar.add("Group");
        }
    }

    /**
     * Adds Diet category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfDietMatches(final Animal animal1,
                                  final Animal animal2,
                                  final Set<String> similar) {
        if (animal1.getDiet().equals(animal2.getDiet())
                || animal1.getDiet().isEmpty()
                || animal2.getDiet().isEmpty()) {
            similar.add("Diet");
        }
    }

    /**
     * Adds Lifestyle category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfLifestyleMatches(final Animal animal1,
                                       final Animal animal2,
                                       final Set<String> similar) {
        if (animal1.getLifestyle().equals(animal2.getLifestyle())
                || animal1.getLifestyle().isEmpty()
                || animal2.getLifestyle().isEmpty()) {
            similar.add("Lifestyle");
        }
    }

    /**
     * Adds Location category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfLocationMatches(final Animal animal1,
                                      final Animal animal2,
                                      final Set<String> similar) {
        if (hasLocationOverlap(animal1.getLocation(),
                animal2.getLocation())) {
            similar.add("Location");
        }
    }

    /**
     * Adds Prey category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfPreyMatches(final Animal animal1,
                                  final Animal animal2,
                                  final Set<String> similar) {
        if (hasPreyOverlap(animal1.getPrey(), animal2.getPrey())) {
            similar.add("Prey");
        }
    }

    /**
     * Adds Habitat category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfHabitatMatches(final Animal animal1,
                                     final Animal animal2,
                                     final Set<String> similar) {
        if (animal1.getHabitat().equals(animal2.getHabitat())
                || animal1.getHabitat().isEmpty()
                || animal2.getHabitat().isEmpty()) {
            similar.add("Habitat");
        }
    }

    /**
     * Adds Lifespan category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfLifespanMatches(final Animal animal1,
                                      final Animal animal2,
                                      final Set<String> similar) {
        final double lifespanDiff = relativeDiff(
                animal1.getLifespan(), animal2.getLifespan());
        if (lifespanDiff < COMPATIBILITY_THRESHOLD) {
            similar.add("Lifespan");
        }
    }

    /**
     * Adds Height category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfHeightMatches(final Animal animal1,
                                    final Animal animal2,
                                    final Set<String> similar) {
        final double heightDiff = relativeDiff(
                animal1.getHeight(), animal2.getHeight());
        if (heightDiff < COMPATIBILITY_THRESHOLD) {
            similar.add("Height");
        }
    }

    /**
     * Adds Weight category if animals match.
     *
     * @param animal1 first animal
     * @param animal2 second animal
     * @param similar set to add to if match
     */
    private void addIfWeightMatches(final Animal animal1,
                                    final Animal animal2,
                                    final Set<String> similar) {
        final double weightDiff = relativeDiff(
                animal1.getWeight(), animal2.getWeight());
        if (weightDiff < COMPATIBILITY_THRESHOLD) {
            similar.add("Weight");
        }
    }

    /**
     * Calculates conflicting categories.
     *
     * @param similar set of similar categories
     * @return set of conflicting categories
     */
    private Set<String> calculateConflictingCategories(
            final Set<String> similar) {
        final Set<String> conflicting = new HashSet<>();
        for (final String category : EXPECTED_CATEGORIES) {
            if (!similar.contains(category)) {
                conflicting.add(category);
            }
        }
        return conflicting;
    }

    /**
     * Calculates compatibility rating as a percentage.
     *
     * @param matchingCount number of matching categories
     * @return rating percentage
     */
    private int calculateRating(final int matchingCount) {
        final double ratio = ((double) matchingCount)
                / EXPECTED_CATEGORIES.length;
        return (int) (ratio * PERCENTAGE_MULTIPLIER);
    }

    /**
     * Checks if two location arrays have any overlap.
     *
     * @param location1 first location array
     * @param location2 second location array
     * @return true if locations overlap
     */
    private boolean hasLocationOverlap(final String[] location1,
                                       final String[] location2) {
        final boolean result;

        if (location1.length == 0 || location2.length == 0) {
            result = true;
        }
        else if ("Worldwide".equals(location1[0])
                || "Worldwide".equals(location2[0])) {
            result = true;
        }
        else {
            result = checkLocationArrayOverlap(location1, location2);
        }

        return result;
    }

    /**
     * Checks if two location arrays have common elements.
     *
     * @param location1 first location array
     * @param location2 second location array
     * @return true if arrays share common elements
     */
    private boolean checkLocationArrayOverlap(final String[] location1,
                                              final String[] location2) {
        boolean hasOverlap = false;
        for (final String loc1 : location1) {
            for (final String loc2 : location2) {
                if (loc1.equals(loc2)) {
                    hasOverlap = true;
                    break;
                }
            }
            if (hasOverlap) {
                break;
            }
        }
        return hasOverlap;
    }

    /**
     * Checks if two prey arrays have any overlap.
     *
     * @param prey1 first prey array
     * @param prey2 second prey array
     * @return true if prey overlap
     */
    private boolean hasPreyOverlap(final String[] prey1,
                                   final String[] prey2) {
        boolean hasOverlap = false;
        for (final String p1 : prey1) {
            for (final String p2 : prey2) {
                if (p1.equals(p2)) {
                    hasOverlap = true;
                    break;
                }
            }
            if (hasOverlap) {
                break;
            }
        }
        return hasOverlap;
    }

    /**
     * Calculates relative difference between two values.
     *
     * @param value1 first value
     * @param value2 second value
     * @return relative difference
     */
    private double relativeDiff(final double value1,
                                final double value2) {
        final double result;
        if (value1 == 0 && value2 == 0) {
            result = 0;
        }
        else {
            result = Math.abs(value1 - value2)
                    / ((value1 + value2) / HALF);
        }
        return result;
    }
}