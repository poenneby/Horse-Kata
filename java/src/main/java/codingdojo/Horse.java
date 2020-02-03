package codingdojo;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Horse {

    /**
     * This method is called by the front end when it wants to display a page of horse data.
     */
    public static PaginatedTable FilterSortPaginateTable(
            List<String> headers,
            List<List<Object>> tableData,
            List<FilterMetadata> filters,
            Optional<SortMetadata> maybeSortMetadata,
            PaginationMetadata paginationMetadata) {

        // TODO: sort horse table using sortMetadata

        // TODO: paginate horse table using paginationMetadata

        // Map all the data to Strings for the front end
        List<List<String>> tableDataAsStrings = tableData
                .stream()
                .filter(row -> {
                    return filters.isEmpty() || applyFiltersOnRow(headers, filters, row);
                })
                .map(row -> row
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        maybeSortMetadata.ifPresent(sortMetadata -> {
            tableDataAsStrings.sort((o1, o2) -> {
                if (sortMetadata.sortOrder.equals("Ascending")) {
                    return o1.get(2).compareTo(o2.get(2));
                }
                return o2.get(2).compareTo(o1.get(2));
            });

        });

        return new PaginatedTable(headers, tableDataAsStrings, tableDataAsStrings.size());
    }



    private static boolean applyFiltersOnRow(List<String> headers, List<FilterMetadata> filters, List<Object> row) {
        return filters.stream()
                .anyMatch(filter -> {
                            int indexOfColumn = getColumnIndex(headers, filter);
                            return row.get(indexOfColumn).equals(filter.value);
                        }
                );
    }

    private static int getColumnIndex(List<String> headers, FilterMetadata filter) {
        return headers.indexOf(filter.columnHeader);
    }
}







