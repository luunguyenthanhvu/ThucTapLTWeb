package nhom55.hcmuaf.dao.daoimpl;

import java.util.List;
import nhom55.hcmuaf.dao.LogDao;
import nhom55.hcmuaf.database.JDBIConnector;
import nhom55.hcmuaf.log.Log;

public class LogDaoImpl<T> implements LogDao<T> {

  @Override
  public void insertLog(Log<T> log) {
      JDBIConnector.get().withHandle(handle -> {
        handle.createUpdate("INSERT INTO logs (ip, level,national,note, address, preValue, currentValue, createAt, updateAt) "
                +
                "VALUES (:ip, :level,:national,:note , :address, :preValue, :curValue, :createAt, :updateAt)")
            .bind("ip", log.getRequestInfo().getIp())
            .bind("level", log.getLevel().toString()) // Convert enum to string
            .bind("address", log.getRequestInfo().getAddress())
            .bind("national", log.getRequestInfo().getNation())
            .bind("note", log.getNote())
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
