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
public class Logs {

  Integer id;
  String ip;
  String level;
  String address;
  String national;
  String note;
  String preValue;
  String currentValue;
  Date createAt;
  Date updateAt;
}
