package nhom55.hcmuaf.dto.request;

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
public class AddNewShipmentRequestDTO {

  List<ShipmentDetailsRequestDTO> data;
  String noteText;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ShipmentDetailsRequestDTO {

    Integer id;
    Integer quantity;
    double importPrice;
  }
}
