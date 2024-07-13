package nhom55.hcmuaf.dto.data_table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTableResponseDTO {

  Integer draw;
  Integer recordsTotal;
  Integer recordsFiltered;
  Object data;
}
