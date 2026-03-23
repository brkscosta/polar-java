package sh.polar.sdk.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Generic paginated list response from Polar API.
 *
 * @param <T> the type of items in the list
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarListResponse<T>(
        @JsonProperty("items") List<T> items,
        @JsonProperty("pagination") PolarPagination pagination
) {
    /**
     * Pagination metadata from Polar API.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PolarPagination(
            @JsonProperty("total_count") int totalCount,
            @JsonProperty("max_page") int maxPage
    ) {
    }

    /**
     * Returns an empty list response.
     */
    public static <T> PolarListResponse<T> empty() {
        return new PolarListResponse<>(List.of(), new PolarPagination(0, 0));
    }
}
