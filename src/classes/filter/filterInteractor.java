/**
 * FilterInteractor: gets the animals from the repository, applies filters, sorting, and pagination, then calls the
 * presenter with the output data.
 */
package classes.filter;

import java.util.ArrayList;
import java.util.List;

import classes.filter.FilterHelpers.*;
import classes.retrieveInfo.APIClass;
import classes.retrieveInfo.animal;
import classes.retrieveInfo.animalFactory;

public class filterInteractor implements filterInputBoundary {

    private final animalNamesProviderI nameProvider;
    private final filterOutputBoundary outputBoundary;
    private final APIClass animalProvider;
    private final animalFactory factory;
    private final candidateCache cache;
    private final paginationHelper paginator;

    public filterInteractor(animalNamesProviderI nameProvider,
                            filterOutputBoundary outputBoundary,
                            APIClass animalProvider,
                            candidateCache cache,
                            paginationHelper paginator) {
        this.nameProvider = nameProvider;
        this.outputBoundary = outputBoundary;
        this.animalProvider = animalProvider;
        this.cache = cache;
        this.paginator = paginator;
        this.factory = new animalFactory();
    }

    /**
     * Main business logic.
     * @param input filter request object
     * @return the filter object output
     */
    @Override
    public filterOutput filterAnimals(filterInput input) {
        // Fetch candidates (either fresh or cached)
        List<String> candidates = (input.getCursor() == null)
                ? nameProvider.getCandidateNames(input)
                : cache.get();

        if (input.getCursor() == null) cache.store(candidates, input);

        if (candidates.isEmpty()) {
            final filterOutput emptyResp = new filterOutput(new ArrayList<>(), false, null);
            outputBoundary.present(emptyResp);
            return emptyResp;
        }

        final int startIndex = paginator.getStartIndex(input);
        final List<animal> results = new ArrayList<>();
        final int processed = processCandidates(input, candidates, results, startIndex);

        final paginationHelper.PaginationInfo pageInfo = paginator.calculatePagination(input, candidates.size(), processed);
        filterOutput output = new filterOutput(results, pageInfo.hasMore, pageInfo.nextCursor);
        outputBoundary.present(output);
        return output;
    }

    private int processCandidates(filterInput input, List<String> candidates, List<animal> results, int startIndex) {
        final int pageSize = input.getPageSize();
        final int buffer = 1;
        // small buffer to ensure enough filtered results
        int processedCount = 0;
        // how many results matched filters
        int candidatesProcessed = 0;
        // how many candidates we looked at

        // Build dynamic filter chain
        List<animalFilter> filters = new ArrayList<>();
        filters.add(new groupFilter(input.getGroups()));
        filters.add(new locationFilter(input.getLocations()));
        filters.add(new dietFilter(input.getDiets()));
        filters.add(new lifespanFilter(input.getMinLifespan(), input.getMaxLifespan()));

        for (int i = startIndex; i < candidates.size() && processedCount < pageSize + buffer; i++) {
            candidatesProcessed++;
            final String data = animalProvider.getAnimalData(candidates.get(i));
            if (data == null || data.trim().isEmpty() || data.equals("[]")) {
                continue;
            }

            try {
                final animal a = factory.fromJsonArrayString(data);
                final boolean match = filters.stream().allMatch(f -> f.matches(a));
                if (match) {
                    results.add(a);
                    processedCount++;
                }
            }
            catch (Exception e) {
                // skip invalid animal
            }
        }

        // return the number of candidates scanned, not the number of results
        return candidatesProcessed;
    }

}
