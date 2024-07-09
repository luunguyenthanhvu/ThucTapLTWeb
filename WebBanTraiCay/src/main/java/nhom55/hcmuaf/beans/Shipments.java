package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipments implements Serializable {

  private int id;
  private boolean available;
  private String status;
  private Date dateIn;
  private List<ShipmentDetails> shipmentDetailsList;
}
