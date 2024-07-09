package nhom55.hcmuaf.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDetails implements Serializable {

  private int id;
  private Products products;
  private Shipments shipments;
  private int quantity;
  private boolean available;
}
