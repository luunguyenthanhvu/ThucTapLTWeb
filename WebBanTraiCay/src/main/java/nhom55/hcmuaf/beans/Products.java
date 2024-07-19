package nhom55.hcmuaf.beans;

import nhom55.hcmuaf.log.IModel;
import nhom55.hcmuaf.log.Log;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class Products extends Log<Products> implements Serializable, IModel {

  private int id;
  private String nameOfProduct;
  private String description;
  private double price;
  private double weightDefault;
  private Date dateOfImporting;
  private int expriredDay;
  private List<Image> imageList;
  private int adminCreate;
  private int provider;
  private String category;
  private String importedFruit;
  private String imgPublicId;
  private String imgAssetId;
  private Integer quantityStock;

  public Products() {

  }

  public Products(String nameOfProduct, String description, double price,
      double weightDefault, int provider, int expriredDay, List<Image> imageList) {
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weightDefault = weightDefault;
    this.provider = provider;
    this.expriredDay = expriredDay;
    this.imageList = imageList;
  }

  public Products(int id, String nameOfProduct, String description, double price,
      double weightDefault, int expriredDay, List<Image> imageList, int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weightDefault = weightDefault;
    this.expriredDay = expriredDay;
    this.imageList = imageList;
    this.provider = provider;
  }

  public Products(int id, String nameOfProduct, String description, double price,
      double weightDefault, Date dateOfImporting, int expriredDay,
      List<Image> imageList,
      int adminCreate, int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weightDefault = weightDefault;
    this.dateOfImporting = dateOfImporting;
    this.expriredDay = expriredDay;
    this.imageList = imageList;
    this.adminCreate = adminCreate;
    this.provider = provider;
  }

  public Products(int id, String nameOfProduct, String description, double price,
      double weightDefault, Date dateOfImporting, int expriredDay, int adminCreate,
      int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weightDefault = weightDefault;
    this.dateOfImporting = dateOfImporting;
    this.expriredDay = expriredDay;
    this.adminCreate = adminCreate;
    this.provider = provider;
  }

  public String getImgPublicId() {
    return imgPublicId;
  }

  public void setImgPublicId(String imgPublicId) {
    this.imgPublicId = imgPublicId;
  }

  public String getImgAssetId() {
    return imgAssetId;
  }

  public void setImgAssetId(String imgAssetId) {
    this.imgAssetId = imgAssetId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImportedFruit() {
    return importedFruit;
  }

  public void setImportedFruit(String importedFruit) {
    this.importedFruit = importedFruit;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNameOfProduct() {
    return nameOfProduct;
  }

  public void setNameOfProduct(String nameOfProduct) {
    this.nameOfProduct = nameOfProduct;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getWeightDefault() {
    return weightDefault;
  }

  public void setWeightDefault(double weightDefault) {
    this.weightDefault = weightDefault;
  }

  public Date getDateOfImporting() {
    return dateOfImporting;
  }

  public void setDateOfImporting(Date dateOfImporting) {
    this.dateOfImporting = dateOfImporting;
  }

  public int getExpriredDay() {
    return expriredDay;
  }

  public void setExpriredDay(int expriredDay) {
    this.expriredDay = expriredDay;
  }

  public int getAdminCreate() {
    return adminCreate;
  }

  public void setAdminCreate(int adminCreate) {
    this.adminCreate = adminCreate;
  }

  public int getProvider() {
    return provider;
  }

  public void setProvider(int provider) {
    this.provider = provider;
  }

  public List<Image> getImageList() {
    return imageList;
  }

  public void setImageList(List<Image> imageList) {
    this.imageList = imageList;
  }


  @Override
  public String toString() {
    return "Products{" +
            "id=" + id +
            ", nameOfProduct='" + nameOfProduct + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", weightDefault=" + weightDefault +
            ", dateOfImporting=" + dateOfImporting +
            ", expriredDay=" + expriredDay +
            ", imageList=" + imageList +
            ", adminCreate=" + adminCreate +
            ", provider=" + provider +
            ", category='" + category + '\'' +
            ", importedFruit='" + importedFruit + '\'' +
            ", imgPublicId='" + imgPublicId + '\'' +
            ", imgAssetId='" + imgAssetId + '\'' +
            '}';
  }

  @Override
  public String getTable() {
    return "Products";
  }

  @Override
  public String getBeforeData() {
    return super.getPreValue();
  }

  @Override
  public String GetAfterData() {
    return super.getCurrentValue();
  }
}
