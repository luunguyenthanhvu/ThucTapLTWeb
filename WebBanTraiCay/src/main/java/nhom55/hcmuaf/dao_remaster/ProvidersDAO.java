package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nhom55.hcmuaf.beans_remaster.Providers;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMappers({
    @RegisterBeanMapper(Providers.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ProvidersDAO {

  @SqlQuery("SELECT * from providers")
  List<Providers> findAllBy();

  @SqlQuery("SELECT p.providerName FROM providers p WHERE p.id = :id")
  Providers findProviderNameById(@Bind("id") int id);
}
