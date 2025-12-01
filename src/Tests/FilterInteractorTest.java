package Tests;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Classes.Filter.*;
import Classes.Filter.FilterHelpers.*;
import Classes.retrieveInfo.*;

class FilterInteractorTest {

    private FilterInteractor interactor;
    private FakeNameProvider nameProvider;
    private FakeOutputBoundary outputBoundary;
    private FakeAnimalProvider animalProvider;
    private CandidateCache cache;
    private FakePaginator paginator;

    @BeforeEach
    void setUp() {
        nameProvider = new FakeNameProvider();
        outputBoundary = new FakeOutputBoundary();
        animalProvider = new FakeAnimalProvider();
        cache = new CandidateCache();
        paginator = new FakePaginator();
        interactor = new FilterInteractor(nameProvider, outputBoundary, animalProvider, cache, paginator);
    }

    @Test
    void testNoCandidates() {
        nameProvider.candidates = Collections.emptyList();
        final FilterInput input = new FilterInput.Builder()
                .build();

        final FilterOutput output = interactor.filterAnimals(input);

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

        FilterInput input = new FilterInput.Builder()
                .build();
        paginator.nextPageInfo = new PaginationHelper.PaginationInfo(2, null, false);

        FilterOutput output = interactor.filterAnimals(input);

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

        final FilterInput input = new FilterInput.Builder().build();
        paginator.nextPageInfo = new PaginationHelper.PaginationInfo(2, null, false);

        final FilterOutput output = interactor.filterAnimals(input);

        assertEquals(1, output.getFilteredAnimals().size());
        assertEquals("Tiger", output.getFilteredAnimals().get(0).getName());
        assertEquals(output, outputBoundary.lastOutput);
    }

    @Test
    void testFilterInvalidJson() {
        nameProvider.candidates = Collections.singletonList("UnknownAnimal");
        animalProvider.data.put("UnknownAnimal", "INVALID_JSON");

        final FilterInput input = new FilterInput.Builder().build();
        paginator.nextPageInfo = new PaginationHelper.PaginationInfo(1, null, false);

        final FilterOutput output = interactor.filterAnimals(input);

        assertTrue(output.getFilteredAnimals().isEmpty());
        assertEquals(output, outputBoundary.lastOutput);
    }

    static class FakeNameProvider implements AnimalNamesProviderI {
        List<String> candidates = new ArrayList<>();

        @Override
        public List<String> getCandidateNames(FilterInput input) {
            return candidates;
        }
    }

    static class FakeOutputBoundary implements FilterOutputBoundary {
        FilterOutput lastOutput;

        @Override
        public void present(FilterOutput output) {
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

    static class FakePaginator extends PaginationHelper {
        PaginationInfo nextPageInfo = new PaginationInfo(0, null, false);

        @Override
        public int getStartIndex(FilterInput input) {
            return 0;
        }

        @Override
        public PaginationInfo calculatePagination(FilterInput input, int totalCandidates, int fetchedCount) {
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
