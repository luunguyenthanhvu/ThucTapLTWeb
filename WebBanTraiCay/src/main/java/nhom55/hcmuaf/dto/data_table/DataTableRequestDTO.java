package nhom55.hcmuaf.dto.data_table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTableRequestDTO {

  private Integer draw;
  private Integer start;
  private Integer length;
  private String searchText;
  private String category;
  private List<ColumnDTO> columns;
  private List<OrderDTO> order;
  private SearchDTO search;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ColumnDTO {

    private Integer data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private SearchDTO search;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OrderDTO {

    private Integer column;
    private String dir;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SearchDTO {

    private String value;
    private Boolean regex;
    private List<String> fixed;
  }
}
