package nhom55.hcmuaf.dao;

import java.sql.Timestamp;
import java.util.List;
import nhom55.hcmuaf.Log.Log;

public interface LogDao<T> {
  public void insertLog(T model);
  void delete(int id);
  List<Log> show();
}
