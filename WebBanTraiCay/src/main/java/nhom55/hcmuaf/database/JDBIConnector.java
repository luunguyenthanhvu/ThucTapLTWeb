package nhom55.hcmuaf.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JDBIConnector {

  private static Jdbi jdbi;

  private static void makeConnect() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setURL(
        "jdbc:mysql://" + DBProperties.getDbHost() + ":" + DBProperties.getDbPort() + "/"
            + DBProperties.getDbName());
    dataSource.setUser(DBProperties.getUsername());
    dataSource.setPassword(DBProperties.getPassword());
    try {
      dataSource.setUseCompression(true);
      dataSource.setAutoReconnect(true);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      throw new RuntimeException(throwables);
    }
    jdbi = Jdbi.create(dataSource);
    jdbi.installPlugin(new SqlObjectPlugin());
  }


  private JDBIConnector() {
  }

  public static Jdbi get() {
    if (jdbi == null) {
      makeConnect();
    }
    return jdbi;
  }

  public static void main(String[] args) {
//        List<User> users = JDBIConnector.get().withHandle(handle -> {
//            return handle.createQuery("select * from user")
//                    .mapToBean(User.class).stream().collect(Collectors.toList());
//        });
//        System.out.println(users);
  }
}



