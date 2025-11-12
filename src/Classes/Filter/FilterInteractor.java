/**
 * FilterInteractor: todo
  /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import java.util.*;
import java.util.stream.Collectors;
import Classes.Animal;

public class FilterInteractor {
    private FilterRepositoryI repo;

    //constructor
    public FilterInteractor(FilterRepositoryI repo){ this.repo = repo; }

//    public FilterOutput handleFilter(FilterInput input){
//        List<String> candidateNames = repo.getCandidateNames(input);
//        List<Animal> animals = candidateNames.stream()
//                .map(repo::getAnimalData)
//                .collect(Collectors.toList());
//        boolean hasMore = candidateNames.size() == input.getLimit();
//        String nextCursor = hasMore ? candidateNames.get(candidateNames.size()-1) : null;
//        return new FilterOutput(animals, hasMore, nextCursor);
//    }
}
