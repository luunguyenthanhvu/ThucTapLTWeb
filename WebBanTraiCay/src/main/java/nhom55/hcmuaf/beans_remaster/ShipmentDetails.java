package nhom55.hcmuaf.beans_remaster;

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
public class ShipmentDetails {

  Integer id;
  Integer productId;
  Integer shipmentsId;
  Integer quantity;
  String status;

}
