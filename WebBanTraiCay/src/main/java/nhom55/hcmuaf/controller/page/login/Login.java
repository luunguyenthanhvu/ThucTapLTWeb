package nhom55.hcmuaf.controller.page.login;

import java.time.LocalDateTime;
import java.util.Map;
import nhom55.hcmuaf.beans.LoginBean;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.cart.CartProduct;
import nhom55.hcmuaf.beans.cart.UserCart;
import nhom55.hcmuaf.dao.daoimpl.LoginDao;
import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/page/login")
public class Login extends  HttpServlet  {

    private LoginDao loginDao = new LoginDao();
    private UsersDao usersDao = new UsersDaoImpl();

    public Login() {
        super();
    }

    /**
     * Show Login page.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has
     *                 made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet
     *                 sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * When the user enters userName & password, and click Submit. This method will be executed.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has
     *                 made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet
     *                 sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (checkValidate(request, response, email, password)) {
            LoginBean loginBean = new LoginBean(email, password, MyUtils.encodePass(password));
            AbsDAO absDAO = AbsDAO.getInstance();
            Log<Users> usersLog = new Log<>();
            RequestInfo requestInfo = new RequestInfo(request.getRemoteAddr(),"HCM", "VietNam");
            String infoAccount = "Tài khoản: "+email +", Mật khẩu: "+MyUtils.encodePass(password);
            String result = loginDao.authorizeLogin(loginBean);
            if (result.equals("FAIL")) {
                usersLog.setNote("Nhập sai tài khoản hoặc mật khẩu");
                usersLog.setLevel(LogLevels.ALERT);
                usersLog.setCurrentValue(infoAccount);
                usersLog.setCreateAt(LocalDateTime.now());
                usersLog.setUpdateAt(null);
                usersLog.setIp(requestInfo.getIp());
                usersLog.setNational(requestInfo.getNation());
                usersLog.setAddress(requestInfo.getAddress());
                absDAO.insert(usersLog);
                request.setAttribute("result", "Sai Email hoặc sai mật khẩu!");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/login/login.jsp");
                dispatcher.forward(request, response);
            } else if (result.equals("ACCOUNT INACTIVE")) {
                usersLog.setNote("Tài khoản chưa được kích hoạt");
                usersLog.setLevel(LogLevels.ALERT);
                usersLog.setCurrentValue(infoAccount);
                usersLog.setCreateAt(LocalDateTime.now());
                usersLog.setUpdateAt(null);
                usersLog.setIp(requestInfo.getIp());
                usersLog.setNational(requestInfo.getNation());
                usersLog.setAddress(requestInfo.getAddress());
                absDAO.insert(usersLog);
                request.setAttribute("result",
                        "Tài khoản chưa xác thực! Vui lòng kiểm tra email!");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/login/login.jsp");
                dispatcher.forward(request, response);
            } else {
                request.removeAttribute("result");
                HttpSession session = request.getSession();
                Users user = UserService.getInstance().getUserByEmail(email);
                MyUtils.storeLoginedUser(session, user);

                UserCart.updateCart(user.getId(), session);

                if (result.equals("ADMIN")) {
                    usersLog.setNote("Admin: "+user.getUsername()+" đăng nhập vào hệ thống");
                    usersLog.setLevel(LogLevels.ALERT);
                    usersLog.setCurrentValue(infoAccount);
                    usersLog.setCreateAt(LocalDateTime.now());
                    usersLog.setUpdateAt(null);
                    usersLog.setIp(requestInfo.getIp());
                    usersLog.setNational(requestInfo.getNation());
                    usersLog.setAddress(requestInfo.getAddress());
                    absDAO.insert(usersLog);
                    // redirect to admin page
                    MyUtils.setUserRole(session, "Admin");
                    response.sendRedirect(request.getContextPath() + "/admin/profile");
                } else if (result.equals("USER")) {
                    usersLog.setNote("User: "+user.getUsername()+" vào trang web");
                    usersLog.setLevel(LogLevels.ALERT);
                    usersLog.setCurrentValue(infoAccount);
                    usersLog.setCreateAt(LocalDateTime.now());
                    usersLog.setUpdateAt(null);
                    usersLog.setIp(requestInfo.getIp());
                    usersLog.setNational(requestInfo.getNation());
                    usersLog.setAddress(requestInfo.getAddress());
                    absDAO.insert(usersLog);
                    // redirect to home
                    MyUtils.setUserRole(session, "User");
                    response.sendRedirect(request.getContextPath() + "/page/home");
                }  else if (result.equals("MANAGER")) {
                    usersLog.setNote("Manager: "+user.getUsername()+" đăng nhập vào hệ thống");
                    usersLog.setLevel(LogLevels.ALERT);
                    usersLog.setCurrentValue(infoAccount);
                    usersLog.setCreateAt(LocalDateTime.now());
                    usersLog.setUpdateAt(null);
                    usersLog.setIp(requestInfo.getIp());
                    usersLog.setNational(requestInfo.getNation());
                    usersLog.setAddress(requestInfo.getAddress());
                    absDAO.insert(usersLog);
                    // redirect to home
                    MyUtils.setUserRole(session, "Manager");
                    response.sendRedirect(request.getContextPath() + "/admin/profile");

                }
            }
        }
        // khong checkValidate
        else {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * check validate for form input
     *
     * @param email
     * @param password
     * @return
     */
    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                         String email, String password) {
        String checkEmail = UserValidator.validateEmail(email);
        String checkPassword = UserValidator.validateMatKhau(password);

        // count for validate
        int count = 0;

        if (!checkEmail.isEmpty()) {
            count++;
            request.setAttribute("error_email", checkEmail);
        } else {
            request.setAttribute("email_user", email);
        }

        if (!checkPassword.isEmpty()) {
            count++;
            request.setAttribute("error_password", checkPassword);
        } else {
            request.setAttribute("pass_user", password);
        }

        if (count > 0) {
            return false;
        }
        return true;
    }

    private static Map<Integer, CartProduct> getResultCart(Map<Integer, CartProduct> map1,
                                                           Map<Integer, CartProduct> map2) {
        for (Map.Entry<Integer, CartProduct> entry : map1.entrySet()) {
            int productId = entry.getKey();
            CartProduct cartProduct1 = entry.getValue();

            // Check if map2 contains the same product
            if (map2.containsKey(productId)) {
                CartProduct cartProduct2 = map2.get(productId);

                // Increase quantity in cartProduct2
                cartProduct2.increQuantity(cartProduct1.getQuantity());
            } else {
                // If map2 doesn't contain the product, add it
                map2.put(productId, cartProduct1);
            }
        }
        return map2;
    }
}
