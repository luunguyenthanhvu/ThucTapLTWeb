package nhom55.hcmuaf.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nhom55.hcmuaf.beans.Image;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.dao.ImageDao;
import nhom55.hcmuaf.dao.ProductDao;
import nhom55.hcmuaf.dao.daoimpl.ImageDaoImpl;
import nhom55.hcmuaf.dao.daoimpl.ProductDaoImpl;

public class ProductService {

  private static ProductService instance;
  static Map<String, String> data = new HashMap<>();
  private ProductDao productDao;
  private ImageDao imageDao;

  private ProductService() {
    productDao = new ProductDaoImpl();
    imageDao = new ImageDaoImpl();
  }

  public static ProductService getInstance() {
    if (instance == null) {
      instance = new ProductService();
    }
    return instance;
  }

  public void addNewProduct(Products products, List<String> imgList) {
    int id = productDao.addNewProduct(products.getNameOfProduct(), products.getDescription(),
        products.getPrice(), products.getWeight(),
        products.getWeightDefault(), products.getDateOfImporting(),
        products.getExpriredDay(), products.getAdminCreate(), products.getProvider());

    List<Image> imageList = new ArrayList<>();
    imgList.forEach(img -> imageList.add(new Image(id, img)));
    imageDao.addImageProduct(imageList);
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
    return productDao.addMoreWeight(id, weight);
  }

  /**
   * get revenue
   */
  public double getTotalMoneyMonth(int month) {
    return productDao.getTotalMoneyMonth(month);
  }

}

