package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nhom55.hcmuaf.beans_remaster.Shipments;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMappers({
    @RegisterBeanMapper(Shipments.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ShipmentsDAO {

  @SqlQuery("SELECT * FROM shipments")
  List<Shipments> findAllBy();


}
