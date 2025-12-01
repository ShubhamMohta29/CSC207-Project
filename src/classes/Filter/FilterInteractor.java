/**
 * FilterInteractor: gets the animals from the repository, applies filters, sorting, and pagination, then calls the
 * presenter with the output data.
 */
package classes.Filter;

import classes.retrieveInfo.ApiClass;
import classes.retrieveInfo.Animal;
import classes.retrieveInfo.AnimalFactory;

import java.util.*;

public class FilterInteractor implements FilterInputBoundary {

    //field declarations
    private final AnimalNamesProviderI nameProviderObj;
    private final FilterOutputBoundary outputBoundary;
    private final ApiClass animalProviderObj;
    private final AnimalFactory factory;

    //specifically for caching logic
    private List<String> cachedCandidates = new ArrayList<>();
    private FilterInput cachedInput;

    /*
    constructor
     */
    public FilterInteractor(AnimalNamesProviderI nameProviderObj, FilterOutputBoundary outputBoundary, ApiClass
            animalProviderObj) {
        this.nameProviderObj = nameProviderObj;
        this.outputBoundary = outputBoundary;
        this.animalProviderObj = animalProviderObj;
        this.factory = new AnimalFactory();
    }

    /*
    Returns the list of animals provided by the LLM  --> necessary for pagination logic
     */
    private List<String> getCachedCandidates() {
        if (cachedCandidates == null || cachedCandidates.isEmpty()) {
            System.out.println("Warning: No cached candidates available");
            return new ArrayList<>(); // Return empty list as fallback
        }
        return cachedCandidates;
    }

    /*
    Helper to check if a list is null or empty
     */
    private boolean isEmptyOrNull(List<?> list) {
        return list == null || list.isEmpty();
    }

    /*
    Core business logic: filter animals
     */
    @Override
    public FilterOutput filterAnimals(FilterInput input) {
        List<Animal> filterResults = new ArrayList<>();
        int pageSize = input.getPageSize();
        System.out.println("im in the filter interactor now");

        List<String> allCandidateAnimals = fetchCandidates(input);

        if (isEmptyOrNull(allCandidateAnimals)) {
            System.out.println("my api didn't return anything");
            FilterOutput emptyResp = new FilterOutput(filterResults, false, null);
            outputBoundary.present(emptyResp);
            return emptyResp;
        }

        // Pagination calculation
        int startIndex = getStartIndex(input);
        System.out.println("Starting from index: " + startIndex + " of " + allCandidateAnimals.size());

        // Process candidates with pagination and filter
        int buffer = 1; // small buffer to ensure we fetch enough matches
        int candidatesProcessed = processCandidates(input, allCandidateAnimals, filterResults, pageSize, startIndex, buffer);

        // Determine next cursor using pagination helper
        PaginationInfo pageInfo = calculatePagination(input, allCandidateAnimals.size(), candidatesProcessed);

        FilterOutput output = new FilterOutput(filterResults, pageInfo.hasMore, pageInfo.nextCursor);
        outputBoundary.present(output);
        return output;
    }

    /*
    Fetch candidates from LLM or cache
     */
    private List<String> fetchCandidates(FilterInput input) {
        List<String> allCandidateAnimals;
        if (input.getCursor() == null) {
            // First call - get fresh candidates
            allCandidateAnimals = nameProviderObj.getCandidateNames(input);
            cachedCandidates = allCandidateAnimals;
            cachedInput = input;
            System.out.println("First call - Candidate animals: " + allCandidateAnimals);
        } else {
            // Load More call - use cached candidates
            allCandidateAnimals = getCachedCandidates();
            System.out.println("Load More - Using cached candidates: " + allCandidateAnimals);
        }
        return allCandidateAnimals;
    }

    /*
    Calculate starting index for pagination
     */
    private int getStartIndex(FilterInput input) {
        int startIndex = 0;
        if (input.getCursor() != null) {
            try {
                startIndex = Integer.parseInt(input.getCursor());
            } catch (NumberFormatException e) {
                startIndex = 0;
            }
        }
        return startIndex;
    }

    /*
    Processes candidate strings into Animal objects, applies filters, and populates results
     */
    private int processCandidates(FilterInput input, List<String> allCandidateAnimals,
                                  List<Animal> filterResults, int pageSize, int startIndex, int buffer) {
        int targetFetchCount = pageSize + buffer;
        int processedCount = 0;
        int candidatesProcessed = 0;

        for (int i = startIndex; i < allCandidateAnimals.size() && processedCount < targetFetchCount; i++) {
            String candidate = allCandidateAnimals.get(i);
            System.out.println("Processing candidate #" + i + ": " + candidate);
            candidatesProcessed++;

            String data = animalProviderObj.getAnimalData(candidate);
            if (data == null || data.trim().isEmpty() || data.equals("[]")) {
                System.out.println("Skipping animal - no data returned for: " + candidate);
                continue;
            }

            System.out.println("Data received for: " + candidate);

            try {
                Animal a = factory.fromJsonArrayString(data);
                if (matchesFilters(a, input)) {
                    System.out.println("It matched my filter: " + a.getName());
                    filterResults.add(a);
                    processedCount++;
                }
            } catch (Exception e) {
                System.out.println("Failed to create Animal from data for: " + candidate);
            }
        }

        System.out.println("Successfully processed " + filterResults.size() + " animals from index " + startIndex);
        System.out.println("Candidates processed this batch: " + candidatesProcessed);
        return candidatesProcessed;
    }

    /*
    Private method to check if each animal matches the requested filter
     */
    private boolean matchesFilters(Animal a, FilterInput request) {
        // Group filter
        if (!isEmptyOrNull(request.getGroups()) &&
                (!request.getGroups().contains(a.getGroup()) && !request.getGroups().contains(a.getType()))) return false;

        // Location filter
        if (!isEmptyOrNull(request.getLocations())) {
            boolean locationMatch = false;
            for (String location : request.getLocations()) {
                if (Arrays.asList(a.getLocation()).contains(location)) {
                    locationMatch = true;
                    break;
                }
            }
            if (!locationMatch) return false;
        }

        // Diet filter
        if (!isEmptyOrNull(request.getDiets()) && !request.getDiets().contains(a.getDiet())) return false;

        // Lifespan filter
        if (request.getMinLifespan() != null && a.getLifespan() < request.getMinLifespan()) return false;
        if (request.getMaxLifespan() != null && a.getLifespan() > request.getMaxLifespan()) return false;

        return true;
    }

    /*
     * Pagination helper: calculates next cursor and whether there are more results
     */
    private PaginationInfo calculatePagination(FilterInput input, int totalCandidates, int fetchedCount) {
        int startIndex = getStartIndex(input);
        int nextIndex = startIndex + fetchedCount;
        boolean hasMore = nextIndex < totalCandidates;
        String nextCursor = hasMore ? String.valueOf(nextIndex) : null;

        System.out.println("Pagination - startIndex: " + startIndex + ", nextIndex: " + nextIndex +
                ", hasMore: " + hasMore + ", nextCursor: " + nextCursor);

        return new PaginationInfo(startIndex, nextCursor, hasMore);
    }

    /*
     * Simple helper DTO (data transfer object) to hold pagination info
     */
    private static class PaginationInfo {
        int startIndex;
        String nextCursor;
        boolean hasMore;

        PaginationInfo(int startIndex, String nextCursor, boolean hasMore) {
            this.startIndex = startIndex;
            this.nextCursor = nextCursor;
            this.hasMore = hasMore;
        }
    }

}
