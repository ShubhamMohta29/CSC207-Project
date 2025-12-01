package Classes.Filter.FilterHelpers;


import java.util.List;

import Classes.Filter.FilterInput;

public class CandidateCache {
    private List<String> cachedCandidates;
    private FilterInput cachedInput;

    /**
     * Stores cached animals.
     * @param candidates returned by the llm
     * @param input the fulter input object
     */
    public void store(List<String> candidates, FilterInput input) {
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

