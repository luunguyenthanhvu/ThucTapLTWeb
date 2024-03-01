package nhom55.hcmuaf.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.util.MyUtils;

public class SecurityFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String servletPath = request.getServletPath();
    System.out.println("Servlet Path: " + servletPath);
    String pathintfo = request.getPathInfo();
    System.out.println("Path Info: " + pathintfo);

    // Thông tin người dùng đã được lưu trong Session
    // (Sau khi đăng nhập thành công).
    Users user = (Users) MyUtils.getLoginedUser(request.getSession());

    if (servletPath.equals("/login")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (user != null) {
      // User Name
      String userName = user.getUsername();

      // Các vai trò (Role).
      String roles = MyUtils.getUserRole(request.getSession());
    }

    // Các trang bắt buộc phải đăng nhập.
    if (SecurityHelper.isSecurityPage(request)) {
      System.out.println("Security Page");
      // Nếu người dùng chưa đăng nhập,
      // Redirect (chuyển hướng) tới trang đăng nhập.
      if (user == null) {
        request.getSession().setAttribute("result", "Bạn cần phải đăng nhập để sử dụng chức năng!");
        request.getSession().setAttribute("status", false);
        response.sendRedirect(request.getContextPath() + "/page/login");
        return;
      }

      // Kiểm tra người dùng có vai trò hợp lệ hay không?
      boolean hasPermission = SecurityHelper.hasPermission(request);
      if (!hasPermission) {
        request.getSession().setAttribute("message1", "Ban khong co quyen");
        request.getSession().setAttribute("message2", "truy cap trang nay!");
        request.getSession().setAttribute("status", "Truy cap bi cam!");
        response.sendRedirect(request.getContextPath() + "/access-for-bidden");
        return;
      }
    } else {
      System.out.println("No Security Page");
    }

    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}