package nhom55.hcmuaf.dao.daoimpl;

import java.util.List;
import nhom55.hcmuaf.beans.Image;
import nhom55.hcmuaf.dao.ImageDao;
import nhom55.hcmuaf.database.JDBIConnector;
import org.jdbi.v3.core.statement.PreparedBatch;

public class ImageDaoImpl implements ImageDao {

  @Override
  public void addImageProduct(List<Image> imageList) {
    JDBIConnector.get().withHandle(handle -> {
      String sql = "INSERT INTO images (idProduct, imgPublicId, imgAssetId) VALUES (:idProduct, :imgPublicId, :imgAssetId)";
      PreparedBatch batch = handle.prepareBatch(sql);
      for (Image img : imageList) {
        batch.bind("idProduct", img.getProductId())
            .bind("imgPublicId", img.getPublicId())
            .bind("imgAssetId", img.getAssetId()).add();
      }
      batch.execute();
      return null;
    });
  }


  @Override
  public void getAllImage() {

  }
}
