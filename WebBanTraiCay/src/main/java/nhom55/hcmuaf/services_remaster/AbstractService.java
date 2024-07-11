package nhom55.hcmuaf.services_remaster;

import nhom55.hcmuaf.database.JDBIConnector;
import org.jdbi.v3.core.Handle;

public abstract class AbstractService {

  protected final Handle handle;
  protected final boolean flag;

  public AbstractService() {
    this.handle = JDBIConnector.get().open();
    this.flag = true;
  }

  public AbstractService(Handle handle) {
    this.handle = handle;
    this.flag = false;
  }

  public void begin() {
    if (this.handle != null && this.flag) {
      this.handle.begin();
    }
  }

  public void save() {
    if (this.handle != null && this.flag) {
      this.handle.commit();
    }
  }

  public void rollback() {
    if (this.handle != null && this.flag) {
      this.handle.rollback();
    }
  }

  public void close() {
    if (this.handle != null && this.flag) {
      this.handle.close();
    }
  }
}
