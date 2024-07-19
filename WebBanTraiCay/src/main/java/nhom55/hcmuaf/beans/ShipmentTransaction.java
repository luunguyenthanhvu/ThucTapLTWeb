package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTransaction implements Serializable {

  private int id;
  private Integer shipmentDetailId;
  private String transactionType;
  private int quantity;
  private LocalDateTime transactionDate;
  private Integer createdBy;
  private String note;
}
