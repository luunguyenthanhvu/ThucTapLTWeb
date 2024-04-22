package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Products implements Serializable {

  private int id;
  private String nameOfProduct;
  private String description;
  private double price;
  private double weight;
  private double weightDefault;
  private Date dateOfImporting;
  private Date expriredDay;
  private String img;
  private List<Image> imageList;
  private int adminCreate;
  private int provider;
  private String seasonalFruit;
  private String importedFruit;
  private String driedFruit;


  public Products() {

  }

  public Products(String nameOfProduct, String description, double price, double weight,
      double weightDefault, int provider, Date expriredDay, List<Image> imageList) {
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weight = weight;
    this.weightDefault = weightDefault;
    this.provider = provider;
    this.expriredDay = expriredDay;
    this.imageList = imageList;
  }

  public Products(int id, String nameOfProduct, String description, double price, double weight,
      double weightDefault, Date expriredDay, List<Image> imageList, int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weight = weight;
    this.weightDefault = weightDefault;
    this.expriredDay = expriredDay;
    this.imageList = imageList;
    this.provider = provider;
  }

  public Products(int id, String nameOfProduct, String description, double price, double weight,
      double weightDefault, Date dateOfImporting, Date expriredDay, String img,
      List<Image> imageList,
      int adminCreate, int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weight = weight;
    this.weightDefault = weightDefault;
    this.dateOfImporting = dateOfImporting;
    this.expriredDay = expriredDay;
    this.img = img;
    this.imageList = imageList;
    this.adminCreate = adminCreate;
    this.provider = provider;
  }

  public Products(int id, String nameOfProduct, String description, double price, double weight,
      double weightDefault, Date dateOfImporting, Date expriredDay, String img, int adminCreate,
      int provider) {
    this.id = id;
    this.nameOfProduct = nameOfProduct;
    this.description = description;
    this.price = price;
    this.weight = weight;
    this.weightDefault = weightDefault;
    this.dateOfImporting = dateOfImporting;
    this.expriredDay = expriredDay;
    this.img = img;
    this.adminCreate = adminCreate;
    this.provider = provider;
  }

  public String getSeasonalFruit() {
    return seasonalFruit;
  }

  public void setSeasonalFruit(String seasonalFruit) {
    this.seasonalFruit = seasonalFruit;
  }

  public String getImportedFruit() {
    return importedFruit;
  }

  public void setImportedFruit(String importedFruit) {
    this.importedFruit = importedFruit;
  }

  public String getDriedFruit() {
    return driedFruit;
  }

  public void setDriedFruit(String driedFruit) {
    this.driedFruit = driedFruit;
  }

  public Products(int id, double weight) {
    this.id = id;
    this.weight = weight;
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

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
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

  public Date getExpriredDay() {
    return expriredDay;
  }

  public void setExpriredDay(Date expriredDay) {
    this.expriredDay = expriredDay;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
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
        ", weight=" + weight +
        ", weightDefault=" + weightDefault +
        ", dateOfImporting=" + dateOfImporting +
        ", expriredDay=" + expriredDay +
        ", img='" + img + '\'' +
        ", imageList=" + imageList +
        ", adminCreate=" + adminCreate +
        ", provider=" + provider +
        ", seasonalFruit='" + seasonalFruit + '\'' +
        ", importedFruit='" + importedFruit + '\'' +
        ", driedFruit='" + driedFruit + '\'' +
        '}';
  }
}
