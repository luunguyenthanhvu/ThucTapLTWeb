package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import nhom55.hcmuaf.beans_remaster.ShipmentTransactions;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMappers({@RegisterBeanMapper(ShipmentTransactions.class),
    @RegisterBeanMapper(LocalDate.class), @RegisterBeanMapper(LocalDateTime.class)})
public interface ShipmentTransactionDAO {

  @SqlUpdate(
      "INSERT INTO shipment_transactions (shipmentDetailId, transactionType, quantity, transactionDate, createdBy) "
          +
          "VALUES (:shipmentDetailId, :transactionType, :quantity, :transactionDate, :createdBy)")
  @GetGeneratedKeys
  long insertShipmentTransaction(@BindBean ShipmentTransactions data);
}
