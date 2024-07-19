package nhom55.hcmuaf.mapper.response;

import java.util.List;
import nhom55.hcmuaf.beans_remaster.Products;
import nhom55.hcmuaf.dao_remaster.ShipmentDetailsDAO;
import nhom55.hcmuaf.dto.response.ListProductShopResponseDTO;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListProductShopResponseDTOMapper {

  ListProductShopResponseDTOMapper INSTANCE = Mappers.getMapper(
      ListProductShopResponseDTOMapper.class);

  @Mapping(source = "nameOfProduct", target = "productName")
  @Mapping(source = "id", target = "quantityStock", qualifiedByName = "getQuantityStock")
  ListProductShopResponseDTO toDto(@Context Handle handle, Products products);

  List<ListProductShopResponseDTO> toListDto(@Context Handle handle, List<Products> products);

  @Named("getQuantityStock")
  default Integer getQuantityStock(@Context org.jdbi.v3.core.Handle handle, Integer productId) {
    var list = handle.attach(ShipmentDetailsDAO.class).getShipmentDetails(productId);
    var quantity = 0;
    if (!list.isEmpty()) {
      for (var item : list) {
        quantity += item.getQuantity();
      }
    }
    return quantity;
  }
}
