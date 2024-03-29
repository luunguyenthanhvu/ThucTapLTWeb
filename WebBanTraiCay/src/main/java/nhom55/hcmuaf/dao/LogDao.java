package nhom55.hcmuaf.dao;

import java.util.List;
import nhom55.hcmuaf.model.Log;

public interface LogDao {
  public void insertLog(Log<?> logModel);
  void delete(int id);
  List<Log> show();
}
