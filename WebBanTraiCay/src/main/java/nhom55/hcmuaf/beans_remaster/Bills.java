package nhom55.hcmuaf.beans_remaster;

import java.sql.Date;
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
public class Bills {

  Integer id;
  Date orderedDate;
  String productList;
  String status;
  Integer userId;
  Integer payment;
  String firstName;
  String lastName;
  String streetAddress;
  String city;
  String phoneNumber;
  String email;
  Double totalPrice;
}
