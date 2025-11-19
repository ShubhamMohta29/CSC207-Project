/**
 * AnimalNamesProvider: todo -- > need to determine whether to ake another api query or just build a local json database
 *
 * and query?
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import java.util.List;

public class AnimalNamesProvider implements AnimalNamesProviderI {
    //private static final String FILTER_API_URL;
    //aPI key:  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

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
