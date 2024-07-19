package nhom55.hcmuaf.beans_remaster;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentTransactions {

  Integer id;
  Integer shipmentDetailId;
  String transactionType;
  Integer quantity;
  LocalDateTime transactionDate;
  Integer createdBy;
  String note;
}
