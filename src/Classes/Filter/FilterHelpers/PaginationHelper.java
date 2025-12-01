package Classes.Filter.FilterHelpers;

import Classes.Filter.FilterInput;

public class PaginationHelper {
    /**
     * Helper method.
     * @param input the filter input object
     * @return the index in the list from where the naimsl should be displayed
     */
    public int getStartIndex(FilterInput input) {
        if (input.getCursor() == null) {
            return 0;
        }
        try {
            return Integer.parseInt(input.getCursor());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public PaginationInfo calculatePagination(FilterInput input, int totalCandidates, int fetchedCount) {
        int startIndex = getStartIndex(input);
        int nextIndex = startIndex + fetchedCount;
        boolean hasMore = nextIndex < totalCandidates;
        String nextCursor = hasMore ? String.valueOf(nextIndex) : null;
        return new PaginationInfo(nextIndex, nextCursor, hasMore);
    }

    public static class PaginationInfo {
        public final int nextIndex;
        public final String nextCursor;
        public final boolean hasMore;

        public PaginationInfo(int nextIndex, String nextCursor, boolean hasMore) {
            this.nextIndex = nextIndex;
            this.nextCursor = nextCursor;
            this.hasMore = hasMore;
        }
    }
}

