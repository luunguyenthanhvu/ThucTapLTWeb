package nhom55.hcmuaf.dao.daoimpl;

import java.util.List;
import java.util.stream.Collectors;
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
        batch.bind("idProduct", img.getProductId()).bind("imgPublicId", img.getImgPublicId())
            .bind("imgAssetId", img.getImgAssetId()).add();
      }
      batch.execute();
      return null;
    });
  }

  @Override
  public List<Image> getImageList(int idProduct) {
    return JDBIConnector.get().withHandle(
        h -> h.createQuery("SELECT * FROM images WHERE idProduct = :idProduct")
            .bind("idProduct", idProduct).mapToBean(Image.class).stream()
            .collect(Collectors.toList()));
  }
}
