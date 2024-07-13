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

  public void addNewProduct(Products products, List<String> imgList, List<String> imgPublicId,
      List<String> imgAssetId) {
    int id = productDao.addNewProduct(products.getNameOfProduct(), products.getDescription(),
        products.getPrice(), products.getWeightDefault(),
        products.getDateOfImporting(), products.getExpriredDay(), products.getAdminCreate(),
        products.getProvider(), products.getCategory(),
        products.getImgPublicId(),
        products.getImgAssetId());
    List<Image> imageList = new ArrayList<>();
    for (int i = 0; i < imgList.size(); i++) {
      imageList.add(new Image(id, imgPublicId.get(i), imgAssetId.get(i)));
    }
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
    Products result = productDao.showProductDetails(productId);
    List<Image> imgList = imageDao.getImageList(productId);
    result.setImageList(imgList);
    return result;
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

