package nhom55.hcmuaf.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.dao.ProviderDao;
import nhom55.hcmuaf.database.JDBIConnector;

public class ProviderDaoImpl implements ProviderDao {

  @Override
  public List<Providers> getAllProvider() {
    return JDBIConnector.get().withHandle(handle -> {
      return handle.createQuery("SELECT * FROM providers")
          .mapToBean(Providers.class)
          .stream()
          .collect(Collectors.toList());
    });
  }
  public List<Providers> get20ProvidersForEachPage(int index, int quantityDefault) {
    List<Providers> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    result = JDBIConnector.get().withHandle(h ->
            h.createQuery(
                            "SELECT * FROM providers ORDER BY id DESC LIMIT :start, :quantityDefault")
                    .bind("start", start)
                    .bind("quantityDefault", quantityDefault)
                    .mapToBean(Providers.class)
                    .list()
    );

    return result;
  }

  @Override
  public int countTotalRowProvidersInDatabase() {
    return JDBIConnector.get().withHandle(h ->
            h.createQuery("SELECT COUNT(id) FROM providers").mapTo(Integer.class).one()
    );
  }

  public List<Providers> search(String search, int index, int sizePage) {
    List<Providers> result = JDBIConnector.get().withHandle(handle -> {
      // Mở kết nối đến cơ sở dữ liệu
      handle.begin();
      try {
        // Thực hiện câu lệnh SQL với giá trị của index và sizePage thay thế trực tiếp
        List<Providers> resultList = handle.createQuery(
                        "with testThu as (select ROW_NUMBER() over (order by " + "id"
                                + "  desc) as r,id, providerName, addressOfProvider from providers where providerName like ?)\n"
                                +
                                "\n" +
                                "select * FROM testThu where r between " + (index * sizePage - 19) + " and " + (
                                index * sizePage))
                .bind(0, "%" + search + "%")
                .mapToBean(Providers.class)
                .list();
        // Commit kết nối
        handle.commit();
        return resultList;
      } catch (Exception e) {
        // Xử lý ngoại lệ và rollback kết nối nếu có lỗi
        handle.rollback();
        throw e;
      }
    });
    return result;
  }
  public int countResultSearchingProviders(String txtSearch) {
    return JDBIConnector.get().withHandle(h ->
            h.select("SELECT count(*)  FROM providers where providerName like ?", "%" + txtSearch + "%")
                    .mapTo(Integer.class)
                    .one()

    );
  }
  public void addNewProvider(String name, String address) {
    JDBIConnector.get().withHandle(h -> {
      return h.createUpdate(
                      "INSERT INTO providers(providerName, addressOfProvider) "
                              + "VALUES (:tenNCC, :diaChiNCC)")
              .bind("tenNCC", name)
              .bind("diaChiNCC", address)
              .execute();
    });
  }
  public void updateProvider(String name, String address, int idNCC) {
    JDBIConnector.get().withHandle(h -> {
      return h.createUpdate(
                      "UPDATE providers set providerName = :tenNCC, addressOfProvider = :diaChiNCC where id = :idNCC")
              .bind("tenNCC", name)
              .bind("diaChiNCC", address)
              .bind("idNCC",idNCC)
              .execute();
    });
  }
  public void deleteProvider(int idNCC) {
    JDBIConnector.get().withHandle(h -> {
      return h.createUpdate(
                      "DELETE from providers where id = :idNCC")
              .bind("idNCC",idNCC)
              .execute();
    });
  }
}
