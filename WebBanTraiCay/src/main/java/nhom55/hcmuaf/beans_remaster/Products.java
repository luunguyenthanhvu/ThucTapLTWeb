package nhom55.hcmuaf.beans_remaster;

import java.sql.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import nhom55.hcmuaf.services_remaster.ProductService;

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
  String category;
  Double price;
  Double weightDefault;
  Date dateOfImporting;
  Integer expriredDay;
  String imgAssetId;
  String imgPublicId;
  Integer adminCreate;
  Integer provider;
  Integer status;

  public static void main(String[] args) {
    ProductService productService = new ProductService();
    productService.begin();
    System.out.println(productService.getShipmentDetails(1));
  }
}
