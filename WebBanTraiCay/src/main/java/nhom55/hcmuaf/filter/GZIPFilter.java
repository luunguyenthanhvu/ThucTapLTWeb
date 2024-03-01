package nhom55.hcmuaf.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class GZIPFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if(request instanceof HttpServletResponse) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      String ae = httpServletRequest.getHeader("accept-encoding");
      if(ae != null && ae.indexOf("gzip") != -1) {
        System.out.println("GZIP supported, compressing.");
        GZIPResponseWrapper responseWrapper = new GZIPResponseWrapper(httpServletResponse);
        chain.doFilter(request, responseWrapper);
        responseWrapper.finishResponse();
        return;
      }
      chain.doFilter(request,response);
    }
  }
}
