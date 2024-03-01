package nhom55.hcmuaf.security;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import nhom55.hcmuaf.util.MyUtils;

public class SecurityHelper {

  public static boolean isSecurityPage(HttpServletRequest request) {
    String urlPattern = UrlAnalysis.getUrlPattern(request);
    Set<String> roles = SecurityConfig.getAllRoles();
    System.out.println(MyUtils.getUserRole(request.getSession()));
    for (String role : roles) {
      List<String> urlPatterns = SecurityConfig.getListRole(role);
      if (urlPatterns != null) {
        for (String pattern : urlPatterns) {
          if (matches(urlPattern, pattern)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  // Kiểm tra 'request' này có vai trò phù hợp hay không?
  public static boolean hasPermission(HttpServletRequest request) {
    String urlPattern = UrlAnalysis.getUrlPattern(request);
    String role = MyUtils.getUserRole(request.getSession());
    System.out.println(MyUtils.getUserRole(request.getSession()));

    // Kiểm tra xem người dùng có quyền truy cập vào các đường dẫn của role không
    return SecurityConfig.getListRole(role).stream().anyMatch(pattern -> matches(urlPattern, pattern));
  }

  private static boolean matches(String urlPattern, String pattern) {
    return urlPattern.equals(pattern) || (pattern.endsWith("*") && urlPattern.startsWith(pattern.substring(0, pattern.length() - 1)));
  }
}
