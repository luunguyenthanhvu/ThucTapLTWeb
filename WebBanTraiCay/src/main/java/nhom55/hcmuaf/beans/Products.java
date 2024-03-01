package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.sql.Date;

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
    private int adminCreate;
    private int provider;

    public Products() {

    }

    public Products(int id, String nameOfProduct, String description, double price, double weight, double weightDefault, Date dateOfImporting, Date expriredDay, String img, int adminCreate, int provider) {
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
                ", adminCreate=" + adminCreate +
                ", provider=" + provider +
                '}';
    }

}
