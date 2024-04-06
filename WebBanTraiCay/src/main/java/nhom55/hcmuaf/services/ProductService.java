package nhom55.hcmuaf.services;


import java.util.Date;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.dao.ProductDao;
import nhom55.hcmuaf.dao.ProductDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

  private static ProductService instance;
  static Map<String, String> data = new HashMap<>();
  private ProductDao productDao;

  private ProductService() {
    productDao = new ProductDaoImpl();
  }

  public static ProductService getInstance() {
    if (instance == null) {
      instance = new ProductService();
    }
    return instance;
  }

  public void addNewProduct(String productName, String description,String seasonalFruit,String sourceImport,String driedFruit, double price,
      double weightQuantity,double weightDefault, Date dateImport, Date expirationDate,String imgProduct, int adminId, int provider) {
     productDao.addNewProduct(productName, description,seasonalFruit,sourceImport,driedFruit,price, weightQuantity, weightDefault,dateImport,
             expirationDate,imgProduct, adminId, provider);
  }

  /**
   * get product by product id
   *
   * @param productId
   * @return
   */
  public Products getById(int productId) {
    return productDao.getProductById(productId);
  }

  // In ra 8 sản phẩm trên trang index
  public List<Products> getProduct() {
    return productDao.getProduct();
  }

  // hiển thị chi tiết sản phẩm
  public Products showProductDetails(int productId) {
    return productDao.showProductDetails(productId);
  }

  /**
   * add more quantity
   */
  public boolean addMoreWeight(int id, double weight) {
    return productDao.addMoreWeight(id,weight);
  }

  /**
   * get revenue
   */
  public double getTotalMoneyMonth(int month) {
    return productDao.getTotalMoneyMonth(month);
  }

}