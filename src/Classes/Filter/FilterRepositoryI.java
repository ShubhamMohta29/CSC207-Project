/**
 * FilterRepositoryI: Data Access Interface
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;


import java.util.*;

public interface FilterRepositoryI {

    /*
        getCandidateName: fetches a list of potential animal names that meet the user's filter criteria.
        This is required since the original API has no concept of generating "random" animals that can be filtered
        through. Thus, this method call is to an api that takes in the filter criteria and generates a list of animal
        names potentially satisfying it.

        @param input: applied filters modelled in FilterInput object
        @param cursor: pagination token to keep track to mark when next batch of animals should be loaded
        @param limit: maximum number of items to fetch per request
     */
    List<String> getCandidateNames (FilterInput input, int limit, String cursor);


}
