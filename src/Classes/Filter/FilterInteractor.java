/**
 * FilterInteractor: gets the animals from the repository, applies filters, sorting, and pagination, then calls the
 * presenter with the output data.
  /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import Classes.Animal;

public class FilterInteractor {
    private AnimalNameProviderI repo;

    //constructor
    public FilterInteractor(AnimalNameProviderI repo){ this.repo = repo; }

//    public FilterOutput handleFilter(FilterInput input){
//        List<String> candidateNames = repo.getCandidateNames(input);
//        List<Animal> animals = candidateNames.stream()
//                .map(repo::getAnimalData)
//                .collect(Collectors.toList());
//        boolean hasMore = candidateNames.size() == input.getLimit();
//        String nextCursor = hasMore ? candidateNames.get(candidateNames.size()-1) : null;
//        return new FilterOutput(animals, hasMore, nextCursor);
//    }

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
//                if (a.getLocation().contains(location)) {
//                    locationMatch = true;
//                    break;
//                }
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
        //todo

        return true;
    }
}
