/**
 * FilterInteractor: gets the animals from the repository, applies filters, sorting, and pagination, then calls the
 * presenter with the output data.
 */
package Classes.Filter;

import java.util.ArrayList;
import java.util.List;

import Classes.Filter.FilterHelpers.*;
import Classes.retrieveInfo.APIClass;
import Classes.retrieveInfo.Animal;
import Classes.retrieveInfo.AnimalFactory;

public class FilterInteractor implements FilterInputBoundary {

    private final AnimalNamesProviderI nameProvider;
    private final FilterOutputBoundary outputBoundary;
    private final APIClass animalProvider;
    private final AnimalFactory factory;
    private final CandidateCache cache;
    private final PaginationHelper paginator;

    public FilterInteractor(AnimalNamesProviderI nameProvider,
                            FilterOutputBoundary outputBoundary,
                            APIClass animalProvider,
                            CandidateCache cache,
                            PaginationHelper paginator) {
        this.nameProvider = nameProvider;
        this.outputBoundary = outputBoundary;
        this.animalProvider = animalProvider;
        this.cache = cache;
        this.paginator = paginator;
        this.factory = new AnimalFactory();
    }

    /**
     * Main business logic.
     * @param input filter request object
     * @return the filter object output
     */
    @Override
    public FilterOutput filterAnimals(FilterInput input) {
        // Fetch candidates (either fresh or cached)
        List<String> candidates = (input.getCursor() == null)
                ? nameProvider.getCandidateNames(input)
                : cache.get();

        if (input.getCursor() == null) cache.store(candidates, input);

        if (candidates.isEmpty()) {
            final FilterOutput emptyResp = new FilterOutput(new ArrayList<>(), false, null);
            outputBoundary.present(emptyResp);
            return emptyResp;
        }

        final int startIndex = paginator.getStartIndex(input);
        final List<Animal> results = new ArrayList<>();
        final int processed = processCandidates(input, candidates, results, startIndex);

        final PaginationHelper.PaginationInfo pageInfo = paginator.calculatePagination(input, candidates.size(), processed);
        FilterOutput output = new FilterOutput(results, pageInfo.hasMore, pageInfo.nextCursor);
        outputBoundary.present(output);
        return output;
    }

    private int processCandidates(FilterInput input, List<String> candidates, List<Animal> results, int startIndex) {
        final int pageSize = input.getPageSize();
        final int buffer = 1;
        // small buffer to ensure enough filtered results
        int processedCount = 0;
        // how many results matched filters
        int candidatesProcessed = 0;
        // how many candidates we looked at

        // Build dynamic filter chain
        List<AnimalFilter> filters = new ArrayList<>();
        filters.add(new GroupFilter(input.getGroups()));
        filters.add(new LocationFilter(input.getLocations()));
        filters.add(new DietFilter(input.getDiets()));
        filters.add(new LifespanFilter(input.getMinLifespan(), input.getMaxLifespan()));

        for (int i = startIndex; i < candidates.size() && processedCount < pageSize + buffer; i++) {
            candidatesProcessed++;
            final String data = animalProvider.getAnimalData(candidates.get(i));
            if (data == null || data.trim().isEmpty() || data.equals("[]")) {
                continue;
            }

            try {
                final Animal a = factory.fromJsonArrayString(data);
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
