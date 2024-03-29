package nhom55.hcmuaf.dao;

import java.util.List;
import nhom55.hcmuaf.database.JDBIConnector;
import nhom55.hcmuaf.model.Log;

public class LogDaoImpl implements LogDao{


  @Override
  public void insertLog(Log<?> logModel) {
    JDBIConnector.get().withHandle(handle -> {
      return handle.createUpdate("INSERT INTO logs(ip, level, address, preValue, curValue, createAt, updateAt) " +
              "VALUES (:ip, :level, :address, :preValue, :curValue, :createAt, :updateAt)")
          .bind("ip", logModel.getIp())
          .bind("level", logModel.getLevel().toString())
          .bind("address", logModel.getAddress())
          .bind("preValue", logModel.getPreValue())
          .bind("curValue", logModel.getCurValue())
          .bind("createAt", logModel.getCreateAt())
          .bind("updateAt", logModel.getUpdateAt())
          .execute();
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
