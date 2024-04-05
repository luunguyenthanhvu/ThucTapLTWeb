package nhom55.hcmuaf.dao;

import java.util.List;
import nhom55.hcmuaf.log.Log;

public interface LogDao<T> {
  public void insertLog(T model);
  void delete(int id);
  List<Log> show();
}
