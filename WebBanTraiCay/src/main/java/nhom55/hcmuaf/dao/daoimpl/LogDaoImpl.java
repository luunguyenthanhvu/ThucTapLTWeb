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

    @Override
    public int countTotalRecords() {
        return JDBIConnector.get().withHandle(h ->
            h.createQuery("SELECT COUNT(*) FROM logs")
                .mapTo(Integer.class)
                .one()
        );
    }

    @Override
    public int countFilteredRecords(String searchText, String logLevel) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM logs WHERE 1=1");

        if (searchText != null && !searchText.isEmpty()) {
            sqlBuilder.append(" AND (id LIKE :searchText OR ip LIKE :searchText OR national LIKE :searchText OR note LIKE :searchText OR address LIKE :searchText)");
        }

        if (logLevel != null && !logLevel.isEmpty()) {
            sqlBuilder.append(" AND level LIKE :logLevel");
        }

        String sql = sqlBuilder.toString();

        return JDBIConnector.get().withHandle(h -> {
            var query = h.createQuery(sql);

            if (searchText != null && !searchText.isEmpty()) {
                query.bind("searchText", "%" + searchText + "%");
            }

            if (logLevel != null && !logLevel.isEmpty()) {
                query.bind("logLevel", "%" + logLevel + "%");
            }

            return query.mapTo(Integer.class).one();
        });
    }



    @Override
    public List<Log> filter(int start, int length, String searchText, String logLevel) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM logs WHERE 1=1");

        if (searchText != null && !searchText.isEmpty()) {
            sqlBuilder.append(" AND (id LIKE :searchText OR ip LIKE :searchText OR national LIKE :searchText OR note LIKE :searchText OR address LIKE :searchText)");
        }

        if (logLevel != null && !logLevel.isEmpty()) {
            sqlBuilder.append(" AND level LIKE :logLevel");
        }

        sqlBuilder.append(" LIMIT :length OFFSET :start");

        String sql = sqlBuilder.toString();

        return JDBIConnector.get().withHandle(h -> {
            var query = h.createQuery(sql);

            if (searchText != null && !searchText.isEmpty()) {
                query.bind("searchText", "%" + searchText + "%");
            }

            if (logLevel != null && !logLevel.isEmpty()) {
                query.bind("logLevel", "%" + logLevel + "%");
            }

            query.bind("start", start);
            query.bind("length", length);

            return query.mapToBean(Log.class).list();
        });
    }



}
