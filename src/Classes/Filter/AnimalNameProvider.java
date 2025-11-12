/**
 * AnimalNameProvider: todo -- > need to determine whether to ake another api query or just build a local json database also the object mapper problem
 *
 * and query?
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import java.util.List;

public class AnimalNameProvider implements AnimalNameProvideI{
    //private static final String FILTER_API_URL;

    @Override
    public List<String> getCandidateNames(FilterInput input) {
        return callAPIFilter(input);
    }

    private List<String> callAPIFilter(FilterInput input){
        // build request
        // Call API and parse response for animal names
        // This API understands filter criteria and returns matching animal names
        return List.of();
    }
}
