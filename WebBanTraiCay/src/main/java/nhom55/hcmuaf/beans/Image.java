package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class Image implements Serializable {
  private int id;
  private int productId;
  private String url;

  public Image(int id, String url, int productId) {
    this.id = id;
    this.url = url;
    this.productId = productId;
  }

  public Image() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }
}
