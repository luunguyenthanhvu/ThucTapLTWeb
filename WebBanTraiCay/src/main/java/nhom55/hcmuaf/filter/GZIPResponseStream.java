package nhom55.hcmuaf.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

public class GZIPResponseStream extends ServletOutputStream {

  protected ByteArrayOutputStream baos = null;
  protected GZIPOutputStream gzipStream = null;
  protected boolean closed = false;
  protected HttpServletResponse response = null;
  protected ServletOutputStream output = null;

  public GZIPResponseStream(HttpServletResponse response) throws IOException {
    super();
    closed = false;
    this.response = response;
    this.output = response.getOutputStream();
    baos = new ByteArrayOutputStream();
    gzipStream = new GZIPOutputStream(baos);
  }

  public void close() throws IOException {
    if (closed) {
      throw new IOException("This output stream has already been closed");
    }
    gzipStream.finish();
    byte[] bytes = baos.toByteArray();

    response.addHeader("Content-Length", Integer.toString(bytes.length));
    response.addHeader("Content-Encoding", "gzip");
    output.write(bytes);
    output.flush();
    output.close();
    closed = true;
  }

  public void flush() throws IOException {
    if (closed) {
      throw new IOException("Can't flush a closed output stream");
    }
    gzipStream.flush();
  }

  @Override
  public void write(int b) throws IOException {
    if (closed) {
      throw new IOException("Can't write to a closed output stream");
    }
    gzipStream.write((byte) b);
  }

  public void write(byte[] b) throws IOException {
    write(b, 0, b.length);
  }

  public void write(byte[] b, int off, int length) throws IOException {
    System.out.println("Writing....");
    if (closed) {
      throw new IOException("Can't write to a closed output stream");
    }
    gzipStream.write(b, off, length);
  }
  public boolean closed() {
    return this.closed;
  }

  @Override
  public boolean isReady() {
    return false;
  }

  @Override
  public void setWriteListener(WriteListener writeListener) {

  }

}
