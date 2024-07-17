package nhom55.hcmuaf.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

  public static final String ROLE_ADMIN = "Admin";
  public static final String ROLE_USER = "User";
  public static final String ROLE_MANAGE = "Manager";

  private static final Map<String, List<String>> mapRole = new HashMap<>();

  static {
    init();
  }


  public static void init() {
    List<String> roleUsers = new ArrayList<>();
    roleUsers.add("/page/user/*");
    roleUsers.add("/page/order/*");
    roleUsers.add("/page/bill/*");

    List<String> roleManages = new ArrayList<>();
    roleUsers.forEach(r -> roleManages.add(r));
    roleManages.add("/admin/product/*");
    roleManages.add("/admin/contact-form*");
    roleManages.add("/admin/order/*");
    roleManages.add("/admin/profile/*");
    roleManages.add("/admin/provider/*");
    roleManages.add("/admin/profile*");
    roleManages.add("/admin/shipment/*");

    List<String> roleAdmins = new ArrayList<>();
    roleManages.forEach(r -> roleAdmins.add(r));
    roleUsers.forEach(r -> roleAdmins.add(r));
    roleAdmins.add("/admin/product/*");
    roleAdmins.add("/admin/contact-form*");
    roleAdmins.add("/admin/order/*");
    roleAdmins.add("/admin/profile/*");
    roleAdmins.add("/admin/provider/*");
    roleAdmins.add("/admin/user/*");
    roleAdmins.add("/admin/profile*");
    roleAdmins.add("/admin/monthly-revenue*");
    roleAdmins.add("/admin/shipment/*");

    mapRole.put(ROLE_USER, roleUsers);
    mapRole.put(ROLE_MANAGE, roleManages);
    mapRole.put(ROLE_ADMIN, roleAdmins);

  }

  public static Set<String> getAllRoles() {
    return mapRole.keySet();
  }

  public static List<String> getListRole(String role) {
    return mapRole.get(role);
  }
}
