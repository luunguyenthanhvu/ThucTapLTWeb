package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class Image implements Serializable {
  private String id;
  private int productId;
  private String url;

  public Image(String id, String url, int productId) {
    this.id = id;
    this.url = url;
    this.productId = productId;
  }

  public Image(String id, String url) {
    this.id = id;
    this.url = url;
  }

  public Image(int productId, String url) {
    this.productId = productId;
    this.url = url;
  }

  public Image() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
