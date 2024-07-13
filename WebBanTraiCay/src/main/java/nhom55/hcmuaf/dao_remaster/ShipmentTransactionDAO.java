package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nhom55.hcmuaf.beans_remaster.ShipmentTransactions;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;

@RegisterBeanMappers({
    @RegisterBeanMapper(ShipmentTransactions.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ShipmentTransactionDAO {

}
