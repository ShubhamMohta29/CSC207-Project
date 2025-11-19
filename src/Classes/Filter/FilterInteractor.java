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
    private final AnimalNamesProviderI nameProviderObj;
    private final FilterOutputBoundary outputBoundary;
    private final APIClass animalProviderObj;

    //constructor
    public FilterInteractor(AnimalNamesProviderI nameProviderObj, FilterOutputBoundary outputBoundary, APIClass
            animalProviderObj) {
        this.nameProviderObj = nameProviderObj;
        this.outputBoundary = outputBoundary;
        this.animalProviderObj = animalProviderObj;
    }

    @Override
    public FilterOutput filterAnimals(FilterInput input) {
        List<Animal> filterResults = new ArrayList<>();
        int pageSize = input.getPageSize(); //this is fixed

        //get all potential animal names that satisfy the user's filter request
        List<String> allCandidateAnimals = nameProviderObj.getCandidateNames(input);


        //check if API returns an empty list/null
        if (allCandidateAnimals == null || allCandidateAnimals.isEmpty()) {
            FilterOutput emptyResp = new FilterOutput(filterResults, false, null);
            outputBoundary.present(emptyResp);
            return emptyResp;
        }

        //fetch the animals objects from API
        int buffer = 1; // fetch one extra to check if hasMore
        int targetFetchCount = pageSize + buffer;

        Map<String, Animal> fetchedAnimals = new HashMap<>();

        for (String candidate : allCandidateAnimals) {
            // fetch one animal at a time
            String data = animalProviderObj.getAnimalData(candidate);
            if (data != null) {
                Animal a = new Animal(data);
                // apply filters immediately
                if (matchesFilters(a, input)) {
                    filterResults.add(a);
                }
            }
            else {
                System.err.println("Failed to fetch animal: " + candidate); // log error
            }

            // stop once we have enough for the page + buffer
            if (filterResults.size() >= targetFetchCount) break;
        }


        // 3. Apply pagination

        int startIndex = 0;
        if (input.getCursor() != null) {
            // find index of cursor
            for (int i = 0; i < filterResults.size(); i++) {
                if (filterResults.get(i).getName().equals(input.getCursor())) {
                    startIndex = i + 1; // start after the cursor
                    break;
                }
            }
        }


        int endIndex = Math.min(startIndex + pageSize, filterResults.size());
        List<Animal> pageAnimals = filterResults.subList(startIndex, endIndex);

        // 4. Determine hasMore and nextCursor
        boolean hasMore = endIndex < filterResults.size();
        String nextCursor = hasMore ? pageAnimals.get(pageAnimals.size() - 1).getName() : null;

        // 5. Prepare output
        FilterOutput output = new FilterOutput(pageAnimals, hasMore, nextCursor);
        outputBoundary.present(output);
        return output;
    }


    private Map<String, Animal> fetchAnimals(List<String> allCandidateAnimals){
            Map<String, Animal> result = new HashMap<>();
        for (String animal: allCandidateAnimals) {
            String data = animalProviderObj.getAnimalData(animal);
            if (data != null) {
                result.put(animal, new Animal(data));
            }
        }
        return result;
        }


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
