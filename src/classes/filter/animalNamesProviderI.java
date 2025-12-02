/**
 * FilterRepositoryI: Data Access Interface for the LLM.
 */

package classes.filter;

import java.util.List;

public interface animalNamesProviderI {

    /**
        * The function getCandidateName: fetches a list of potential animal names that meet the user's filter criteria.
        * This is required since the original API has no concept of generating "random" animals that can be filtered
        * through. Thus, this method call is to an api that takes in the filter criteria and generates a list of animal
        * names potentially satisfying it.
        *
        * @param input applied filters modelled in FilterInput object cursor and limit pagination fields are already
        *       coupled with the FilterInput
        * @return returns the list of potential candidates.
     */
    List<String> getCandidateNames(filterInput input);

}
