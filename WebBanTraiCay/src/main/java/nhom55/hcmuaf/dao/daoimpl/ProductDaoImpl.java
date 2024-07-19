package nhom55.hcmuaf.dao.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.dao.ProductDao;
import nhom55.hcmuaf.database.JDBIConnector;

public class ProductDaoImpl implements ProductDao {

  /**
   * get product by id
   *
   * @return
   */
  @Override
  public Products getProductById(int id) {
    return JDBIConnector.get().withHandle(handle -> {
      return handle.createQuery("SELECT * FROM products where ID = :productId")
          .bind("productId", id)
          .mapToBean(Products.class)
          .findOne()
          .orElse(null);
    });
  }

  // Xuất ra toàn bộ sản phẩm lấy từ database
  // In ra 8 sản phẩm trên trang index
  @Override
  public List<Products> getProduct() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT * FROM products ORDER BY dateOfImporting ASC LIMIT 8")
            .mapToBean(Products.class)
            .stream()
            .collect(Collectors.toList())
    );
  }


  // hiển thị chi tiết sản phẩm
  @Override
  public Products showProductDetails(int productId) {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT p.id, p.nameOfProduct, p.description, p.price, p.imgPublicId, p.weightDefault, "
                    + "(SELECT SUM(sd.quantity) FROM shipment_details sd WHERE sd.productId = p.id AND sd.available = 1) as quantityStock "
                    + "FROM products p WHERE p.id = :id")
            .bind("id", productId)
            .map((rs, ctx) -> {
              Products product = new Products();
              product.setId(rs.getInt("id"));
              product.setNameOfProduct(rs.getString("nameOfProduct"));
              product.setDescription(rs.getString("description"));
              product.setPrice(rs.getDouble("price"));
              product.setImgPublicId(rs.getString("imgPublicId"));
              product.setWeightDefault(rs.getDouble("weightDefault"));
              product.setQuantityStock(rs.getInt("quantityStock")); // set thêm quantityStock
              return product;
            })
            .findFirst()
            .orElse(null));
  }


  @Override
  public List<Products> getListProducts() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT * FROM products ")
            .mapToBean(Products.class)
            .stream()
            .collect(Collectors.toList())
    );
  }

  //    Đếm số sản phầm tìm được
  @Override
  public int countResultSearchingProduct(String txtSearch) {
    return JDBIConnector.get().withHandle(h ->
        h.select(
                "SELECT count(*)  FROM products where nameOfProduct like ? and expriredDay >= CURDATE() ",
                "%" + txtSearch + "%")
            .mapTo(Integer.class)
            .one()

    );
  }

  @Override
  public int countResultSearchingProductForExpiredProduct(String txtSearch) {
    return JDBIConnector.get().withHandle(h ->
        h.select(
                "SELECT count(*)  FROM products where nameOfProduct like ? ",
                "%" + txtSearch + "%")
            .mapTo(Integer.class)
            .one()

    );
  }

  //   tìm kiếm của shop
  @Override
  public List<Products> search(String search, int index, int sizePage) {
    List<Products> result = JDBIConnector.get().withHandle(handle -> {
      handle.begin();
      try {
        int startIndex = (index - 1) * sizePage + 1;
        int endIndex = index * sizePage;

        List<Products> resultList = handle.createQuery(
                "WITH testThu AS (" +
                    "SELECT ROW_NUMBER() OVER (ORDER BY dateOfImporting DESC) AS r, " +
                    "id, nameOfProduct, description, price, weight, weightDefault, " +
                    "dateOfImporting, expriredDay, img, adminCreate, provider " +
                    "FROM products WHERE nameOfProduct LIKE ? AND expriredDay >= CURDATE())\n" +
                    "SELECT * FROM testThu WHERE r BETWEEN ? AND ?")
            .bind(0, "%" + search + "%")
            .bind(1, startIndex)
            .bind(2, endIndex)
            .mapToBean(Products.class)
            .list();

        handle.commit();
        return resultList;
      } catch (Exception e) {
        handle.rollback();
        throw e;
      }
    });
    return result;
  }


  @Override
  public List<Products> searchFilter(String sortBy, String order, String search, int index,
      int sizePage) {
    List<Products> resultList = JDBIConnector.get().withHandle(h ->
        h.createQuery("with testThu as (select ROW_NUMBER() over (order by " + sortBy + " " + order
                + ") as r, id, nameOfProduct, description, price, weight, weightDefault, dateOfImporting, expriredDay, img, adminCreate, provider from products where nameOfProduct like :search  AND expriredDay >= CURDATE())\n"
                +
                "\n" +
                "select * FROM testThu where r between :startIndex and :endIndex")
            .bind("search", "%" + search + "%")
            .bind("startIndex", (index * sizePage - 19))
            .bind("endIndex", (index * sizePage))
            .mapToBean(Products.class)
            .list());

    return resultList;
  }

  //    Lấy 20 sản phẩm cho mỗi trang
  @Override
  public List<Products> get20ProductsForEachPage(int index, int quantityDefault) {
    List<Products> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    result = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT * FROM products ORDER BY nameOfProduct where DESC LIMIT :start, :quantityDefault")
            .bind("start", start)
            .bind("quantityDefault", quantityDefault)
            .mapToBean(Products.class)
            .list()
    );

    return result;
  }


  //    Đếm Số dòng record của tất cả sản phẩm trong database
  @Override
  public int countTotalRowProductInDatabase() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT COUNT(id) FROM products ")
            .mapTo(Integer.class).one()
    );
  }

  @Override
  public int countTotalRowProductInDatabaseForExpiredProduct() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT COUNT(id) FROM products ")
            .mapTo(Integer.class).one()
    );
  }


  //    Filter
//    Sắp xếp theo điều kiện filter (option: tên, giá, ngày nhập khẩu, filter:asc,desc)
  @Override
  public List<Products> sortByFilter(int index, int quantityDefault, String order,
      String whereClause) {
    List<Products> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    // Tạo chuỗi orderByClause
    String orderByClause = "";
    if (!order.isEmpty()) {
      orderByClause = " ORDER BY " + order;

    }

    // Tạo câu lệnh SQL
    String query = "SELECT * FROM products ";
    if (whereClause != null && !("".equals(whereClause))) {

      query += " And " + whereClause;
    }
    query += orderByClause + " LIMIT :start, :quantityDefault";

    // Sử dụng biến final để sử dụng trong lambda expression
    final String finalQuery = query;

    // Thực hiện truy vấn SQL và trả về kết quả
    result = JDBIConnector.get().withHandle(h ->
        h.createQuery(finalQuery)
            .bind("start", start)
            .bind("quantityDefault", quantityDefault)
            .mapToBean(Products.class)
            .list()
    );

    return result;
  }

  @Override
  public int addNewProduct(String productName, String description, double price,
      double weightDefault, Date dateImport, int expirationDate,
      int adminId, int provider, String category,
      String imgPublicId, String imgAssetId) {
    return
        JDBIConnector.get().withHandle(h -> h.createUpdate(
                "INSERT INTO products(nameOfProduct, description, price, weightDefault, dateOfImporting, expriredDay, adminCreate, provider, category, imgPublicId, imgAssetId) "
                    + "VALUES (:nameOfProduct, :description, :price, :weightDefault, :dateOfImporting, :expriredDay, :adminCreate, :provider, :category, :imgPublicId, :imgAssetId)")
            .bind("nameOfProduct", productName)
            .bind("description", description)
            .bind("price", price)
            .bind("weightDefault", weightDefault)
            .bind("dateOfImporting", dateImport)
            .bind("expriredDay", expirationDate)
            .bind("adminCreate", adminId)
            .bind("provider", provider)
            .bind("category", category)
            .bind("imgPublicId", imgPublicId)
            .bind("imgAssetId", imgAssetId)
            .executeAndReturnGeneratedKeys("id")
            .mapTo(int.class)
            .one());
  }
//   Phần phục vụ cho quản lý sản phẩm của admin

  public void editProductNoImage(int idProduct, String name, String des, String mua,
      String nguonNhap, String driedFruit, double giaTien,
      double khoiLuong, double soKgMacDinh, Date ngayNhapKho, Date ngayHetHan, int idAdmin,
      int idnhaCungCap) {
    if (nguonNhap.equals("local")) {
      JDBIConnector.get().withHandle(h ->
          h.createUpdate(
                  "UPDATE products SET nameOfProduct = :name, description = :des, seasonalFruit = :mua,domesticFruit = :nguonNhap,driedFruit = :driedFruit,price = :giaTien, "
                      +
                      "weight = :khoiLuong, weightDefault = :soKgMacDinh,dateOfImporting =:ngayNhapKho, expriredDay = :ngayHetHan, adminCreate = :idAdmin, provider = :idnhaCungCap "
                      +
                      "WHERE id = :idProduct")
              .bind("name", name)
              .bind("des", des)
              .bind("mua", mua)
              .bind("nguonNhap", nguonNhap)
              .bind("driedFruit", driedFruit)
              .bind("giaTien", giaTien)
              .bind("khoiLuong", khoiLuong)
              .bind("soKgMacDinh", soKgMacDinh)
              .bind("ngayNhapKho", ngayNhapKho)
              .bind("ngayHetHan", ngayHetHan)
              .bind("idAdmin", idAdmin)
              .bind("idProduct", idProduct)
              .bind("idnhaCungCap", idnhaCungCap)
              .execute()
      );
    } else {
      JDBIConnector.get().withHandle(h ->
          h.createUpdate(
                  "UPDATE products SET nameOfProduct = :name, description = :des, seasonalFruit = :mua,importedFruit = :nguonNhap,driedFruit =:driedFruit,price = :giaTien, "
                      +
                      "weight = :khoiLuong, weightDefault = :soKgMacDinh,dateOfImporting =:ngayNhapKho, expriredDay = :ngayHetHan, adminCreate = :idAdmin, provider = :idnhaCungCap "
                      +
                      "WHERE id = :idProduct")
              .bind("name", name)
              .bind("des", des)
              .bind("mua", mua)
              .bind("nguonNhap", nguonNhap)
              .bind("driedFruit", driedFruit)
              .bind("giaTien", giaTien)
              .bind("khoiLuong", khoiLuong)
              .bind("soKgMacDinh", soKgMacDinh)
              .bind("ngayNhapKho", ngayNhapKho)
              .bind("ngayHetHan", ngayHetHan)
              .bind("idAdmin", idAdmin)
              .bind("idProduct", idProduct)
              .bind("idnhaCungCap", idnhaCungCap)
              .execute()
      );
    }


  }

  public void editProductHaveImage(int idProduct, String name, String des, String mua,
      String nguonNhap, String driedFruit, double giaTien,
      double khoiLuong, double soKgMacDinh, Date ngayNhapKho, Date ngayHetHan, String tenAnh,
      int idAdmin, int idnhaCungCap) {
    if (nguonNhap.equals("local")) {
      JDBIConnector.get().withHandle(h ->
          h.createUpdate(
                  "UPDATE products SET nameOfProduct = :name, description = :des,  seasonalFruit = :mua,domesticFruit = :nguonNhap,driedFruit =:driedFruit,price = :giaTien, "
                      +
                      "weight = :khoiLuong, weightDefault = :soKgMacDinh,dateOfImporting =:ngayNhapKho, expriredDay = :ngayHetHan,img =:tenAnh, adminCreate = :idAdmin, provider = :idnhaCungCap "
                      +
                      "WHERE id = :idProduct")
              .bind("name", name)
              .bind("des", des)
              .bind("des", des)
              .bind("mua", mua)
              .bind("nguonNhap", nguonNhap)
              .bind("driedFruit", driedFruit)
              .bind("giaTien", giaTien)
              .bind("khoiLuong", khoiLuong)
              .bind("soKgMacDinh", soKgMacDinh)
              .bind("ngayNhapKho", ngayNhapKho)
              .bind("ngayHetHan", ngayHetHan)
              .bind("tenAnh", tenAnh)
              .bind("idAdmin", idAdmin)
              .bind("idProduct", idProduct)
              .bind("idnhaCungCap", idnhaCungCap)
              .execute()
      );
    } else {
      JDBIConnector.get().withHandle(h ->
          h.createUpdate(
                  "UPDATE products SET nameOfProduct = :name, description = :des,  seasonalFruit = :mua,importedFruit = :nguonNhap,driedFruit =:driedFruit,price = :giaTien, "
                      +
                      "weight = :khoiLuong, weightDefault = :soKgMacDinh,dateOfImporting =:ngayNhapKho, expriredDay = :ngayHetHan,img =:tenAnh, adminCreate = :idAdmin, provider = :idnhaCungCap "
                      +
                      "WHERE id = :idProduct")
              .bind("name", name)
              .bind("des", des)
              .bind("des", des)
              .bind("mua", mua)
              .bind("nguonNhap", nguonNhap)
              .bind("driedFruit", driedFruit)
              .bind("giaTien", giaTien)
              .bind("khoiLuong", khoiLuong)
              .bind("soKgMacDinh", soKgMacDinh)
              .bind("ngayNhapKho", ngayNhapKho)
              .bind("ngayHetHan", ngayHetHan)
              .bind("tenAnh", tenAnh)
              .bind("idAdmin", idAdmin)
              .bind("idProduct", idProduct)
              .bind("idnhaCungCap", idnhaCungCap)
              .execute()
      );
    }

  }


  public void deleteProduct(int idProduct) {
    JDBIConnector.get().withHandle(h ->
        h.createUpdate("DELETE FROM products WHERE id = :idProduct")
            .bind("idProduct", idProduct).execute()
    );
  }

  public List<Products> printExpiredProduct(int index, int quantityDefault) {
    List<Products> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    result = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT * FROM products WHERE expriredDay <= CURDATE() ORDER BY dateOfImporting DESC LIMIT :start, :quantityDefault")
            .bind("start", start)
            .bind("quantityDefault", quantityDefault)
            .mapToBean(Products.class)
            .list()
    );

    return result;
  }

  public List<Products> searchExpiredProduct(String search, int index, int sizePage) {
    List<Products> result = JDBIConnector.get().withHandle(handle -> {
      handle.begin();
      try {
        int startIndex = (index - 1) * sizePage + 1;
        int endIndex = index * sizePage;

        String sql = "WITH testThu AS (" +
            "SELECT ROW_NUMBER() OVER (ORDER BY dateOfImporting DESC) AS r, id, nameOfProduct, description, price, weight, weightDefault, dateOfImporting, expriredDay, img, adminCreate, provider "
            +
            "FROM products WHERE nameOfProduct LIKE ? AND expriredDay <= CURDATE()" +
            ") " +
            "SELECT * FROM testThu WHERE r BETWEEN ? AND ?";

        List<Products> resultList = handle.createQuery(sql)
            .bind(0, "%" + search + "%")
            .bind(1, startIndex)
            .bind(2, endIndex)
            .mapToBean(Products.class)
            .list();

        handle.commit();
        return resultList;
      } catch (Exception e) {
        handle.rollback();
        throw e;
      }
    });
    return result;
  }

  @Override
  public boolean addMoreWeight(int id, double weight) {
//    Products product = JDBIConnector.get()
//        .withHandle(h -> h.createQuery("SELECT * FROM products WHERE id = :id")
//            .bind("id", id)
//            .mapToBean(Products.class)
//            .findFirst()
//            .orElse(null));
//    if (product != null) {
//      return JDBIConnector.get().withHandle(handle -> {
//        int rowsUpdate = handle.createUpdate("UPDATE products SET weight = :weight WHERE id = :id")
//            .bind("weight", product.getWeight() + weight)
//            .bind("id", id)
//            .execute();
//        return rowsUpdate > 0;
//      });
//    }
    return false;
  }

  @Override
  public double getTotalMoneyMonth(int month) {
    Optional<Double> result = JDBIConnector.get().withHandle(h -> h.createQuery(
            "SELECT SUM(totalPrice) FROM bills WHERE MONTH(orderedDate) = :month")
        .bind("month", month)
        .mapTo(Double.class)
        .findOne());

    return result.orElse(0.0); // Nếu result là null thì trả về giá trị mặc định là 0.0
  }


}
