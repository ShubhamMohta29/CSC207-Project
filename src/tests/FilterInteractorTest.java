package tests;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import classes.filter.*;
import classes.filter.FilterHelpers.*;
import classes.retrieveInfo.*;

class FilterInteractorTest {

    private filterInteractor interactor;
    private FakeNameProvider nameProvider;
    private FakeOutputBoundary outputBoundary;
    private FakeAnimalProvider animalProvider;
    private candidateCache cache;
    private FakePaginator paginator;

    @BeforeEach
    void setUp() {
        nameProvider = new FakeNameProvider();
        outputBoundary = new FakeOutputBoundary();
        animalProvider = new FakeAnimalProvider();
        cache = new candidateCache();
        paginator = new FakePaginator();
        interactor = new filterInteractor(nameProvider, outputBoundary, animalProvider, cache, paginator);
    }

    @Test
    void testNoCandidates() {
        nameProvider.candidates = Collections.emptyList();
        final filterInput input = new filterInput.Builder()
                .build();

        final filterOutput output = interactor.filterAnimals(input);

        assertNotNull(output);
        assertTrue(output.getFilteredAnimals().isEmpty());
        assertFalse(output.hasMore());
        assertNull(output.getNextCursor());
        assertEquals(output, outputBoundary.lastOutput);
    }

    @Test
    void testWithCandidates() {
        nameProvider.candidates = Arrays.asList("Tiger", "Elephant");
        animalProvider.data.put("Tiger", "[{\"name\":\"Tiger\",\"characteristics\":{\"diet\":\"Carnivore\"},\"taxonomy\":{}}]");
        animalProvider.data.put("Elephant", "[{\"name\":\"Elephant\",\"characteristics\":{\"diet\":\"Herbivore\"},\"taxonomy\":{}}]");

        filterInput input = new filterInput.Builder()
                .build();
        paginator.nextPageInfo = new paginationHelper.PaginationInfo(2, null, false);

        filterOutput output = interactor.filterAnimals(input);

        assertEquals(2, output.getFilteredAnimals().size());
        assertFalse(output.hasMore());
        assertEquals(output, outputBoundary.lastOutput);
    }

    @Test
    void testFilterSomeFilteredOut() {
        nameProvider.candidates = Arrays.asList("Tiger", "Elephant");
        animalProvider.data.put("Tiger", "[{\"name\":\"Tiger\",\"characteristics\":{\"diet\":\"Carnivore\"},\"taxonomy\":{}}]");
        animalProvider.data.put("Elephant", "[]");
        // filtered out

        final filterInput input = new filterInput.Builder().build();
        paginator.nextPageInfo = new paginationHelper.PaginationInfo(2, null, false);

        final filterOutput output = interactor.filterAnimals(input);

        assertEquals(1, output.getFilteredAnimals().size());
        assertEquals("Tiger", output.getFilteredAnimals().get(0).getName());
        assertEquals(output, outputBoundary.lastOutput);
    }

    @Test
    void testFilterInvalidJson() {
        nameProvider.candidates = Collections.singletonList("UnknownAnimal");
        animalProvider.data.put("UnknownAnimal", "INVALID_JSON");

        final filterInput input = new filterInput.Builder().build();
        paginator.nextPageInfo = new paginationHelper.PaginationInfo(1, null, false);

        final filterOutput output = interactor.filterAnimals(input);

        assertTrue(output.getFilteredAnimals().isEmpty());
        assertEquals(output, outputBoundary.lastOutput);
    }

    static class FakeNameProvider implements animalNamesProviderI {
        List<String> candidates = new ArrayList<>();

        @Override
        public List<String> getCandidateNames(filterInput input) {
            return candidates;
        }
    }

    static class FakeOutputBoundary implements filterOutputBoundary {
        filterOutput lastOutput;

        @Override
        public void present(filterOutput output) {
            lastOutput = output;
        }
    }

    static class FakeAnimalProvider extends APIClass {
        Map<String, String> data = new HashMap<>();

        @Override
        public String getAnimalData(String animalName) {
            return data.getOrDefault(animalName, "[]");
        }
    }

    static class FakePaginator extends paginationHelper {
        PaginationInfo nextPageInfo = new PaginationInfo(0, null, false);

        @Override
        public int getStartIndex(filterInput input) {
            return 0;
        }

        @Override
        public PaginationInfo calculatePagination(filterInput input, int totalCandidates, int fetchedCount) {
            int pageSize = input.getPageSize();
            // use actual page size from FilterInput
            int itemsOnPage = Math.min(pageSize, totalCandidates);
            // only return up to page size
            boolean hasMore = totalCandidates > itemsOnPage;
            String nextCursor = hasMore ? String.valueOf(itemsOnPage) : null;
            return new PaginationInfo(itemsOnPage, nextCursor, hasMore);
        }
    }
}
