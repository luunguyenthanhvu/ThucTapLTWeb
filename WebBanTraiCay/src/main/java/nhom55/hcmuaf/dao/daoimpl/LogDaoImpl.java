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
                "VALUES (:ip, :level,:national,:note , :address, :preValue, :currentValue, :createAt, :updateAt)")
            .bind("ip", log.getIp())
            .bind("level", log.getLevel().toString()) // Convert enum to string
            .bind("address", log.getAddress())
            .bind("national", log.getNational())
            .bind("note", log.getNote())
            .bind("preValue", log.getPreValue())
            .bind("currentValue", log.getCurrentValue())
            .bind("createAt", log.getCreateAt())
            .bind("updateAt", log.getUpdateAt())
            .execute();
        return null;
      });
  }

  @Override
  public void delete(int idLog) {
 JDBIConnector.get().withHandle( h ->{
   return h.createUpdate("DELETE FROM logs WHERE id = :idLog").bind("idLog", idLog).execute();
 });
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
