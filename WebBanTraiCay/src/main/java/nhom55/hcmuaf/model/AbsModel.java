package nhom55.hcmuaf.model;

import nhom55.hcmuaf.dao.LogDao;
import nhom55.hcmuaf.dao.LogDaoImpl;

public abstract class AbsModel<T> implements IModel{
  private LogDao logDao;

  public AbsModel() {
    this.logDao = new LogDaoImpl();
  }

  @Override
  public void insert(Log<?> logModel) {
    // Ghi log vào cơ sở dữ liệu bằng cách sử dụng LogDao
    try {
      logDao.insertLog(logModel);
      System.out.println("Log inserted successfully.");
    } catch (Exception e) {
      System.err.println("Error inserting log: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
