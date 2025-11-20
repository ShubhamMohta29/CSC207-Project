/**
 * FilterInteractor: gets the animals from the repository, applies filters, sorting, and pagination, then calls the
 * presenter with the output data.
  /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import Classes.APIClass;
import Classes.Animal;

import java.util.*;

public class FilterInteractor implements FilterInputBoundary {

    //field declarations
    private final AnimalNamesProviderI nameProviderObj;
    private final FilterOutputBoundary outputBoundary;
    private final APIClass animalProviderObj;

    //specifically for caching logic
    private List<String> cachedCandidates = new ArrayList<>();
    private FilterInput cachedInput;

    /*
    constructor
     */
    public FilterInteractor(AnimalNamesProviderI nameProviderObj, FilterOutputBoundary outputBoundary, APIClass
            animalProviderObj) {
        this.nameProviderObj = nameProviderObj;
        this.outputBoundary = outputBoundary;
        this.animalProviderObj = animalProviderObj;

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
    This method performs the core business logic of filtering through the animals
     */
    @Override
    public FilterOutput filterAnimals(FilterInput input) {
        List<Animal> filterResults = new ArrayList<>();
        int pageSize = input.getPageSize();
        System.out.println("im in the filter interactor now");

        List<String> allCandidateAnimals;
        //the problem is every time you click load more button you are sending another call to OpenRouterAPI!!!!!!
        // that's why the cursor and pagination logic is flawed right now. you need to ensure you're calling only
        // once and next time you click load button its retrieving animals from the original list based on teh
        // cursor position (˃̣̣̥ᯅ˂̣̣̥)
        if (input.getCursor() == null) {
            // First call - get fresh candidates from OpenRouter SO THAT WE ONLY END UP CALLING ONCE PER REQUEST (˃̣̣̥ᯅ˂̣̣̥)
            allCandidateAnimals = nameProviderObj.getCandidateNames(input);
            // store in the cache
            cachedCandidates = allCandidateAnimals;
            cachedInput = input;
            System.out.println("First call - Candidate animals: " + allCandidateAnimals);
        } else {
            // Load More call - NOW use cached candidates YAY
            allCandidateAnimals = getCachedCandidates();
            System.out.println("Load More - Using cached candidates: " + allCandidateAnimals);
        }

        if (allCandidateAnimals == null || allCandidateAnimals.isEmpty()) {
            System.out.println("my api didn't return anything");
            FilterOutput emptyResp = new FilterOutput(filterResults, false, null);
            outputBoundary.present(emptyResp);
            return emptyResp;
        }

        // Pagination logic
        int startIndex = 0;

        if (input.getCursor() != null) {
            try {
                startIndex = Integer.parseInt(input.getCursor());
            } catch (NumberFormatException e) {
                startIndex = 0;
            }
        }

        System.out.println("Starting from index: " + startIndex + " of " + allCandidateAnimals.size());

        int buffer = 1;
        int targetFetchCount = pageSize + buffer;
        int processedCount = 0;
        int candidatesProcessed = 0; // Track how many candidates we actually processed UP TO THIS POINT

        // Process candidates starting from startIndex
        for (int i = startIndex; i < allCandidateAnimals.size() && processedCount < targetFetchCount; i++) {
            String candidate = allCandidateAnimals.get(i);
            System.out.println("Processing candidate #" + i + ": " + candidate);
            candidatesProcessed++; // Count every candidate we attempt to process

            String data = animalProviderObj.getAnimalData(candidate);

            if (data == null || data.trim().isEmpty() || data.equals("[]")) {
                System.out.println("Skipping animal - no data returned for: " + candidate);
                continue; // Continue to next candidate (˃̣̣̥ᯅ˂̣̣̥)
            }

            System.out.println("Data received for: " + candidate);

            try {
                Animal a = new Animal(data);
                if (matchesFilters(a, input)) {
                    System.out.println("It matched my filter: " + a.getName());
                    filterResults.add(a);
                    processedCount++; // Only count successful matches toward our target
                }
            } catch (Exception e) {
                System.out.println("Failed to create Animal from data for: " + candidate);
            }
        }

        System.out.println("Successfully processed " + filterResults.size() + " animals from index " + startIndex);

        int nextIndex = startIndex + candidatesProcessed;
        boolean hasMore = nextIndex < allCandidateAnimals.size();
        String nextCursor = hasMore ? String.valueOf(nextIndex) : null;

        System.out.println("Next index: " + nextIndex + ", Has more: " + hasMore + ", Next cursor: " + nextCursor);
        System.out.println("Candidates processed this batch: " + candidatesProcessed);

        FilterOutput output = new FilterOutput(filterResults, hasMore, nextCursor);
        outputBoundary.present(output);
        return output;
    }

    /*
    Private method to check if each animal matches the requested filter
     */
    private boolean matchesFilters(Animal a, FilterInput request) {
        // Group filter
        if (request.getGroups() != null && !request.getGroups().isEmpty()) {
            if (!request.getGroups().contains(a.getGroup())) {
                return false;
            }
        }

        // Location filter
        if (request.getLocations() != null && !request.getLocations().isEmpty()) {
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
        if (request.getDiets() != null && !request.getDiets().isEmpty()) {
            if (!request.getDiets().contains(a.getDiet())) {
                return false;
            }
        }

        //Lifespan filter
        if (request.getMinLifespan() != null && a.getLifespan() < request.getMinLifespan()) return false;
        if (request.getMaxLifespan() != null && a.getLifespan() > request.getMaxLifespan()) return false;


        return true;
    }


}
