package nhom55.hcmuaf.beans_remaster;

import java.sql.Date;
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
public class Users {

  Integer id;
  String username;
  String hash;
  String password;
  String email;
  String address;
  String phoneNum;
  Integer status;
  String img;
  Date dateOfBirth;
  String sexual;
  LocalDateTime creationTime;
  Integer role;
}
