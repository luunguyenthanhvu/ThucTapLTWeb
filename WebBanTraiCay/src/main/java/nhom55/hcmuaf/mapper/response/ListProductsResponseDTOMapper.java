package nhom55.hcmuaf.mapper.response;

import java.util.List;
import nhom55.hcmuaf.beans_remaster.Products;
import nhom55.hcmuaf.dao_remaster.ProvidersDAO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListProductsResponseDTOMapper {

  ListProductsResponseDTOMapper INSTANCE = Mappers.getMapper(ListProductsResponseDTOMapper.class);

  @Mapping(source = "nameOfProduct", target = "productName")
  @Mapping(source = "provider", target = "provider", qualifiedByName = "getProviderName")
  ListProductResponseDTO toDto(@Context Handle handle, Products products);

  List<ListProductResponseDTO> toListDTO(@Context Handle handle, List<Products> products);

  @Named("getProviderName")
  default String getProviderName(@Context Handle handle, int providerId) {
    return handle.attach(ProvidersDAO.class).findProviderNameById(providerId).getProviderName();
  }
}
