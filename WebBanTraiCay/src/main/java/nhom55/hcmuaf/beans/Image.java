package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class Image implements Serializable {

  private String id;
  private int productId;
  private String imgPublicId;
  private String imgAssetId;

  public Image(String id, int productId) {
    this.id = id;
    this.productId = productId;
  }

  public Image(int productId, String imgPublicId, String imgAssetId) {
    this.productId = productId;
    this.imgPublicId = imgPublicId;
    this.imgAssetId = imgAssetId;
  }

  public Image(int productId) {
    this.productId = productId;
  }


  public Image() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getProductId() {
    return productId;
  }


  public void setProductId(int productId) {
    this.productId = productId;
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
}
