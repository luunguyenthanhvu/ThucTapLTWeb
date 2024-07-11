package nhom55.hcmuaf.dao_remaster;

import java.util.List;
import nhom55.hcmuaf.beans_remaster.Users;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface UsersDAO {

  @SqlQuery("SELECT * FROM users")
  @RegisterBeanMapper(Users.class)
  List<Users> findAllBy();

}
