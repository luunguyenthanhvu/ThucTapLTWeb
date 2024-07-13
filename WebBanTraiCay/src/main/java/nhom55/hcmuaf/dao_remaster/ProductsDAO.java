package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nhom55.hcmuaf.beans_remaster.Products;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMappers({
    @RegisterBeanMapper(Products.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ProductsDAO {

  @SqlQuery("SELECT * FROM products")
  List<Products> findAllBy();

  @SqlQuery("SELECT * FROM products WHERE nameOfProduct LIKE :searchText AND category LIKE :category LIMIT :length OFFSET :start")
  List<Products> findAllBy(@Bind("start") int start, @Bind("length") int length,
      @Bind("searchText") String searchText, @Bind("category") String category);

  @SqlQuery("SELECT COUNT(*) FROM products")
  Integer countTotalRecords();

  @SqlQuery("SELECT COUNT(*) FROM products WHERE nameOfProduct LIKE :searchText AND category LIKE :category")
  Integer countFilteredRecords(@Bind("searchText") String searchText,
      @Bind("category") String category);

  @SqlQuery("SELECT * FROM products WHERE id = :id")
  Optional<Products> findAllById(@Bind("id") Integer id);

  @SqlUpdate("UPDATE products set status = :status WHERE id = :id")
  void updateProductStatus(@Bind("status") Integer status, @Bind("id") Integer id);
}
