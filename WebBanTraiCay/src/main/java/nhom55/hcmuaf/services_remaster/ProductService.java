package nhom55.hcmuaf.services_remaster;

import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.beans_remaster.Products;
import nhom55.hcmuaf.dao_remaster.ProductsDAO;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.mapper.response.ListProductsResponseDTOMapper;
import org.jdbi.v3.core.Handle;

@Data
@NoArgsConstructor
public class ProductService extends AbstractService {

  public ProductService(Handle handle) {
    super(handle);
  }

  public List<ListProductResponseDTO> findAllBy() {
    List<Products> productsList = handle.attach(ProductsDAO.class).findAllBy();
    return ListProductsResponseDTOMapper.INSTANCE.toListDTO(handle, productsList);
  }

  public List<ListProductResponseDTO> findAllBy(int start, int length, String searchText,
      String category, List<DataTableRequestDTO.OrderDTO> orderDTOS) {
    ProductsDAO productsDAO = handle.attach(ProductsDAO.class);
    String orderBy = productsDAO.buildOrderByClause(orderDTOS);
    System.out.println(orderBy);
    List<Products> productsList = productsDAO.findAllBy(start, length, "%" + searchText + "%",
        "%" + category + "%", orderBy);
    return ListProductsResponseDTOMapper.INSTANCE.toListDTO(handle, productsList);
  }


  public Integer countTotalRecords() {
    return handle.attach(ProductsDAO.class).countTotalRecords();
  }

  public Integer countFilteredRecords(String searchText, String category) {
    return handle.attach(ProductsDAO.class)
        .countFilteredRecords("%" + searchText + "%", "%" + category + "%");
  }

  public Optional<Products> findAllById(Integer id) {
    return handle.attach(ProductsDAO.class).findAllById(id);
  }

  public void updateProductStatus(Integer status, Integer productId) {
    handle.attach(ProductsDAO.class).updateProductStatus(status, productId);
  }
}
