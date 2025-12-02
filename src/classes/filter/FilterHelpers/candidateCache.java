package classes.filter.FilterHelpers;


import java.util.List;

import classes.filter.filterInput;

public class candidateCache {
    private List<String> cachedCandidates;
    private filterInput cachedInput;

    /**
     * Stores cached animals.
     * @param candidates returned by the llm
     * @param input the fulter input object
     */
    public void store(List<String> candidates, filterInput input) {
        this.cachedCandidates = candidates;
        this.cachedInput = input;
    }

    public List<String> get() {
        return cachedCandidates == null ? List.of() : cachedCandidates;
    }

    public boolean isEmpty() {
        return cachedCandidates == null || cachedCandidates.isEmpty();
    }
}

