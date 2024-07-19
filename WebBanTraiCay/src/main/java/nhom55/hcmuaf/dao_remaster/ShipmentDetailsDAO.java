package nhom55.hcmuaf.dao_remaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nhom55.hcmuaf.beans_remaster.ShipmentDetails;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMappers({
    @RegisterBeanMapper(ShipmentDetails.class),
    @RegisterBeanMapper(LocalDate.class),
    @RegisterBeanMapper(LocalDateTime.class)
})
public interface ShipmentDetailsDAO {

  @SqlQuery("SELECT * FROM shipment_details s WHERE s.productId = :productId AND s.available = 1")
  List<ShipmentDetails> getShipmentDetails(@Bind("productId") Integer productId);

  @SqlUpdate(
      "INSERT INTO shipment_details(productId, shipmentsId, price, quantity, available, note) "
          + "VALUES (:data.productId, :data.shipmentsId, :data.price, :data.quantity, :data.available, :data.note)")
  @GetGeneratedKeys
  long insert(@BindBean("data") ShipmentDetails shipmentDetails);
}
