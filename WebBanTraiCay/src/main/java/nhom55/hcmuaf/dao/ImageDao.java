package nhom55.hcmuaf.dao;

import java.util.List;
import nhom55.hcmuaf.beans.Image;

public interface ImageDao {
  void addImageProduct(List<Image> imageList);
  List<Image> getImageList(int idProduct);

}
