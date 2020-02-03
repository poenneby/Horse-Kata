package codingdojo;


import com.google.gson.Gson;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HorseTest {

    @Test
    public void FilterSortPaginate_No_Filters_No_Sorting_No_Pagination() throws IOException {
        // Arrange - this is data from another service or database
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Collections.emptyList();
        Optional<SortMetadata> sortMetadata = Optional.empty();
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // Act
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

    @Test
    public void should_filter_result_with_one_filter() throws IOException {
        // Given
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Arrays.asList(
            new FilterMetadata("Breed", "Thoroughbred")
        );
        Optional<SortMetadata> sortMetadata = Optional.empty();
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // When
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        // Then
        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

    @Test
    public void should_filter_result_with_two_filters() throws IOException {
        // Given
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Arrays.asList(
                new FilterMetadata("Breed", "Thoroughbred"),
                new FilterMetadata("Colour", "Black")
        );
        Optional<SortMetadata> sortMetadata = Optional.empty();
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // When
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        // Then
        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

    @Test
    public void should_filter_result_with_invalid_filter() throws IOException {
        // Given
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Arrays.asList(
                new FilterMetadata("Breed", "Jaguar"),
                new FilterMetadata("Colour", "Red")
        );
        Optional<SortMetadata> sortMetadata = Optional.empty();
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // When
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        // Then
        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

    @Test
    public void should_sort_result_with_one_sort_descending() throws IOException {
        // Given
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Collections.emptyList();
        Optional<SortMetadata> sortMetadata = Optional.of(new SortMetadata("Height", "Descending"));
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // When
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        // Then
        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

    @Test
    public void should_sort_result_with_one_sort_ascending() throws IOException {
        // Given
        List<String> headers = SampleHorseData.GetSampleHeaders();
        List<List<Object>> tableData = SampleHorseData.GetSampleTableData();

        // These objects describe the query we got from the front end
        List<FilterMetadata> filters = Collections.emptyList();
        Optional<SortMetadata> sortMetadata = Optional.of(new SortMetadata("Height", "Ascending"));
        PaginationMetadata paginationMetadata = new PaginationMetadata(0, 10);

        // When
        PaginatedTable table = Horse.FilterSortPaginateTable(headers, tableData, filters, sortMetadata, paginationMetadata);

        // Then;
        Gson gson = new Gson();
        String json = gson.toJson(table);
        // Assert the data to be sent to the front end
        Approvals.verifyJson(json);
    }

}

