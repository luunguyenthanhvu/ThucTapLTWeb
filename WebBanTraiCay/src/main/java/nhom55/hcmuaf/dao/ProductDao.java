package nhom55.hcmuaf.dao;

import java.util.Date;
import java.util.List;
import nhom55.hcmuaf.beans.Products;

public interface ProductDao {

  Products getProductById(int id);

  // Xuất ra toàn bộ sản phẩm lấy từ database
  // In ra 8 sản phẩm trên trang index
  List<Products> getProduct();

  // hiển thị chi tiết sản phẩm
  Products showProductDetails(int productId);

  List<Products> getListProducts();

  //    Đếm số sản phầm tìm được
  int countResultSearchingProduct(String txtSearch);

  //   tìm kiếm của shop
  List<Products> search(String search, int index, int sizePage);

  List<Products> searchFilter(String sortBy, String order, String search, int index, int sizePage);

  //    Lấy 20 sản phẩm cho mỗi trang
  List<Products> get20ProductsForEachPage(int index, int quantityDefault);

  //    Đếm Số dòng record của tất cả sản phẩm trong database
  int countTotalRowProductInDatabase();

  //    Filter
//    Sắp xếp theo điều kiện filter (option: tên, giá, ngày nhập khẩu, filter:asc,desc)
  List<Products> sortByFilter(int index, int quantityDefault, String sortBy, String order);

  int addNewProduct(String productName, String description, double price, double weightDefault, Date dateImport, int expirationDate,
      int adminId, int provider, String category,
      String imgPublicId, String imgAssetId);

//   Phần phục vụ cho quản lý sản phẩm của admin

  public void editProductNoImage(int idProduct, String name, String des, String mua,
      String nguonNhap, String driedFruit, double giaTien,
      double khoiLuong, double soKgMacDinh, Date ngayNhapKho, Date ngayHetHan, int idAdmin,
      int idnhaCungCap);

  public void editProductHaveImage(int idProduct, String name, String des, String mua,
      String nguonNhap, String driedFruit, double giaTien,
      double khoiLuong, double soKgMacDinh, Date ngayNhapKho, Date ngayHetHan, String tenAnh,
      int idAdmin, int idnhaCungCap);

  public void deleteProduct(int idProduct);

  public List<Products> printExpiredProduct(int index, int quantityDefault);

  public List<Products> searchExpiredProduct(String search, int index, int sizePage);

  boolean addMoreWeight(int id, double weight);

  double getTotalMoneyMonth(int month);

  public int countTotalRowProductInDatabaseForExpiredProduct();

  public int countResultSearchingProductForExpiredProduct(String txtSearch);
};

