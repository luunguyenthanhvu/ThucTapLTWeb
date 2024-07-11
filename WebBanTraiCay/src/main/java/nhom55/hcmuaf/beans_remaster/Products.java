package nhom55.hcmuaf.beans_remaster;

import java.sql.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {

  Integer id;
  String nameOfProduct;
  String description;
  String seasonalFruit;
  Double price;
  Double weightDefault;
  Date dateOfImporting;
  Integer expriredDay;
  String imgAssetId;
  String imgPublicId;
  Integer adminCreate;
  Integer provider;
  Integer status;
}
