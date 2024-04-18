package nhom55.hcmuaf.dao.daoimpl;

import java.util.List;
import nhom55.hcmuaf.dao.LogDao;
import nhom55.hcmuaf.database.JDBIConnector;
import nhom55.hcmuaf.log.Log;

public class LogDaoImpl<T> implements LogDao<T> {

  @Override
  public void insertLog(T model) {
      Log<T> log = (Log<T>) model;
      JDBIConnector.get().withHandle(handle -> {
        handle.createUpdate("INSERT INTO logs (ip, level, address, pre_value, cur_value, create_at, update_at) " +
                "VALUES (:ip, :level, :address, :preValue, :curValue, :createAt, :updateAt)")
            .bind("ip", log.getIp())
            .bind("level", log.getLevel().toString()) // Convert enum to string
            .bind("address", log.getAddress())
            .bind("preValue", log.getPreValue())
            .bind("curValue", log.getCurValue())
            .bind("createAt", log.getCreateAt())
            .bind("updateAt", log.getUpdateAt())
            .execute();
        return null;
      });
  }

  @Override
  public void delete(int id) {
    JDBIConnector.get().withHandle(h ->
        h.createUpdate("DELETE  FROM  logs WHERE id = :idLog")
            .bind("idLog", id)
    );
  }

  @Override
  public List<Log> show() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT * FROM logs")
            .mapToBean(Log.class)
            .list()
    );
  }
}
