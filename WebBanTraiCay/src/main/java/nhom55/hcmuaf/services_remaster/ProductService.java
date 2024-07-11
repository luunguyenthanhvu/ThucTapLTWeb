package nhom55.hcmuaf.services_remaster;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.beans_remaster.Products;
import nhom55.hcmuaf.dao_remaster.ProductsDAO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.mapper.response.ListProductsResponseDTOMapper;
import org.jdbi.v3.core.Handle;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductService extends AbstractService {

  private ProductsDAO productsDAO;

  public ProductService(Handle handle) {
    super(handle);
  }

  public List<ListProductResponseDTO> findAllBy() {
    List<Products> productsList = handle.attach(ProductsDAO.class).findAllBy();
    return ListProductsResponseDTOMapper.INSTANCE.toListDTO(handle, productsList);
  }
}
