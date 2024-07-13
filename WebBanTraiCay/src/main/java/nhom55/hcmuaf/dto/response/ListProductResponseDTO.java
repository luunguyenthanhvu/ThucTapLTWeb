package nhom55.hcmuaf.dto.response;

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
public class ListProductResponseDTO {

  String imgAssetId;
  String imgPublicId;
  String category;
  String productName;
  Integer id;
  String provider;
  Integer status;
  Integer quantityStock;
}
