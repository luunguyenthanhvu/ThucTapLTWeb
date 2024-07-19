package nhom55.hcmuaf.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.text.NumberFormat;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListProductShopResponseDTO {

  Integer id;
  String imgAssetId;
  String imgPublicId;
  String productName;
  Integer quantityStock;
  Double price;

  public String getFormattedPrice() {
    NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
    return formatter.format(price) + " VNĐ";
  }
}
