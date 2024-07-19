package nhom55.hcmuaf.dto.response;

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
public class ShipmentDetailsResponseDTO {

  Integer id;
  Integer productId;
  String productName;
  Integer shipmentsId;
  Integer quantity;
  Integer available;
  LocalDateTime dateIn;
}
