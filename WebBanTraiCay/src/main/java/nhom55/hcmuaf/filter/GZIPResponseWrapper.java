package nhom55.hcmuaf.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZIPResponseWrapper extends HttpServletResponseWrapper {
  protected HttpServletResponse origResponse = null;
  protected ServletOutputStream stream = null;
  protected PrintWriter writer = null;
  /**
   * Constructs a response adaptor wrapping the given response.
   *
   * @param response the {@link HttpServletResponse} to be wrapped.
   * @throws IllegalArgumentException if the response is null
   */
  public GZIPResponseWrapper(HttpServletResponse response) {
    super(response);
    origResponse = response;
  }
  public ServletOutputStream createOutputStream() throws IOException {
    return (new GZIPResponseStream(origResponse));
  }
  public void finishResponse() {
    try {
      if(writer != null) {
        writer.close();
      } else {
        if(stream != null) {
          stream.close();
        }
      }
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
  public void flushBuffer() throws IOException {
    stream.flush();
  }
  public ServletOutputStream getOutputStream() throws  IOException {
    if(writer != null) {
      throw  new IllegalStateException("getWriter() has already been called!");
    }
    if(stream == null) {
      stream = createOutputStream();
    }
    return stream;
  }
  public PrintWriter getWriter() throws IOException {
    if(writer != null) {
      return writer;
    }
    if (stream != null) {
      throw  new IllegalStateException("getOutputStream() has already been called!");
    }
    stream = createOutputStream();
    writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
    return writer;
  }
}
