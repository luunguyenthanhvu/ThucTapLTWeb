package nhom55.hcmuaf.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.response.MessageResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;

@WebFilter(filterName = "global_3", urlPatterns = "/api/*")
public class GlobalExceptionFilter implements Filter {

  private final String REQUEST_BODY = "request-body";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = servletRequest.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    String requestBody = sb.toString();
    System.out.println("Before convert");
    System.out.println(requestBody);
    PrintWriter out = servletResponse.getWriter();
    httpServletRequest.setAttribute(REQUEST_BODY, requestBody);
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (Exception e) {
      servletResponse.setContentType("json/application");
      servletResponse.setCharacterEncoding("UTF8");
      if (e instanceof MyHandleException) {
        httpServletResponse.setStatus(((MyHandleException) e).getHttpStatusCode());
        out.println(MessageResponseDTO.builder().message(e.getMessage()).build());
      }
    } finally {
      out.flush();
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
