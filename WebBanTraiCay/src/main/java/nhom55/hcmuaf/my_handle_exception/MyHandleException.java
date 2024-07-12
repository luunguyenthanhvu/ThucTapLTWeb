package nhom55.hcmuaf.my_handle_exception;

import javax.servlet.ServletException;
import lombok.Data;

@Data
public class MyHandleException extends ServletException {

  int httpStatusCode;

  public MyHandleException(String message, int httpStatusCode) {
    super(message);
    this.httpStatusCode = httpStatusCode;
  }

}
