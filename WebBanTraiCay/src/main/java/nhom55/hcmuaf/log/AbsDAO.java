package nhom55.hcmuaf.log;

import nhom55.hcmuaf.dao.LogDao;
import nhom55.hcmuaf.dao.LogDaoImpl;

public class AbsDAO <T extends IModel> implements IDAO<T>{
  private static AbsDAO instance;
  private LogDao logDao;

  public AbsDAO() {
   logDao = new LogDaoImpl();
  }
  @Override
  public void insert(T model) {
    logDao.insertLog(model);

  }
}
