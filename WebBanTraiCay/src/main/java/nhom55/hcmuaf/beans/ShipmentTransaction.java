package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.beans.enums.ETransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTransaction implements Serializable {

  private int id;
  private ShipmentDetails shipmentDetails;
  private ETransactionType transactionType;
  private int quantity;
  private Date transactionDate;
  private Users createdBy;
  private String note;
}
