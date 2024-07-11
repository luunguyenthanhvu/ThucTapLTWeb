package nhom55.hcmuaf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListProductResponseDTO {

  String imgAssetId;
  String imgPublicId;
  String productName;
  Integer id;
  String provider;
  Integer status;
}
