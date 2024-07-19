package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import nhom55.hcmuaf.beans_remaster.Products;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.response.ShipmentDetailsResponseDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMappers({
    @RegisterBeanMapper(Products.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ProductsDAO {

  Map<Integer, String> orderForProductList = new HashMap<Integer, String>() {
    {
      put(0, "id");
      put(1, "nameOfProduct");
      put(2, "category");
      put(3, "id");
      put(4, "provider");
      put(5, "quantityStock");
      put(6, "status");
      put(7, "id");
    }
  };


  @SqlQuery("SELECT * FROM products")
  List<Products> findAllBy();

  @SqlQuery("SELECT p.*, "
      + "(SELECT SUM(sd.quantity) FROM shipment_details sd WHERE sd.productId = p.id AND sd.available = 1) as quantityStock "
      + "FROM products p "
      + "WHERE nameOfProduct LIKE :searchText "
      + "AND category LIKE :category "
      + " <order> "
      + "LIMIT :length "
      + "OFFSET :start")
  List<Products> findAllBy(@Bind("start") int start, @Bind("length") int length,
      @Bind("searchText") String searchText, @Bind("category") String category,
      @Define("order") String orderBy);

  @SqlQuery("SELECT p.*, "
      + "(SELECT SUM(sd.quantity) FROM shipment_details sd WHERE sd.productId = p.id AND sd.available = 1) as quantityStock "
      + "FROM products p "
      + "WHERE p.id = :id ")
  Products findProductShopResponseDTO(@Bind("id") Integer id);

  default String buildOrderByClause(List<DataTableRequestDTO.OrderDTO> orders) {
    StringBuilder orderByClause = new StringBuilder();
    boolean first = true;
    if (!orders.isEmpty()) {
      orderByClause.append(" ORDER BY ");
    }
    for (DataTableRequestDTO.OrderDTO order : orders) {
      if (!first) {
        orderByClause.append(", ");
      } else {
        first = false;
      }
      String column = orderForProductList.get(order.getColumn());
      if (column != null && !column.isEmpty()) {
        orderByClause.append(column).append(" ").append(order.getDir());
      }
    }
    return orderByClause.toString();
  }

  @SqlQuery("SELECT COUNT(*) FROM products")
  Integer countTotalRecords();

  @SqlQuery("SELECT COUNT(*) FROM products WHERE nameOfProduct LIKE :searchText AND category LIKE :category")
  Integer countFilteredRecords(@Bind("searchText") String searchText,
      @Bind("category") String category);

  @SqlQuery("SELECT * FROM products WHERE id = :id")
  Optional<Products> findAllById(@Bind("id") Integer id);

  @SqlUpdate("UPDATE products set status = :status WHERE id = :id")
  void updateProductStatus(@Bind("status") Integer status, @Bind("id") Integer id);

  @SqlQuery("SELECT p.*, "
      + "(SELECT SUM(sd.quantity) FROM shipment_details sd WHERE sd.productId = p.id AND sd.available = 1) as quantityStock "
      + "FROM products p "
      + "WHERE p.id = :id ")
  Products findProductDetailsResponseDTO(@Bind("id") Integer id);

  @SqlQuery(
      "SELECT sd.id AS shipmentDetailsId, sd.productId, p.nameOfProduct AS productName, s.id AS shipmentId, sd.quantity, sd.available, s.dateIn AS dateIn "
          + "FROM shipment_details sd "
          + "JOIN shipments s ON sd.shipmentsId = s.id "
          + "JOIN products p ON sd.productId = p.id "
          + "WHERE p.id = :productId")
  @RegisterBeanMapper(ShipmentDetailsResponseDTO.class)
  List<ShipmentDetailsResponseDTO> getShipmentDetails(
      @Bind("productId") Integer productId);
}
