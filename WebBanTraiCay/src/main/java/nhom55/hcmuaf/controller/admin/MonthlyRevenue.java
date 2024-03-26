package nhom55.hcmuaf.controller.admin;

import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "MonthlyRevenue", value = "/admin/monthly-revenue")
public class MonthlyRevenue extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    HttpSession session = request.getSession();
    Users admin = MyUtils.getLoginedUser(session);

    double totalMoneyMonth1 = ProductService.getInstance().getTotalMoneyMonth(1);
    double totalMoneyMonth2 = ProductService.getInstance().getTotalMoneyMonth(2);
    double totalMoneyMonth3 = ProductService.getInstance().getTotalMoneyMonth(3);
    double totalMoneyMonth4 = ProductService.getInstance().getTotalMoneyMonth(4);
    double totalMoneyMonth5 = ProductService.getInstance().getTotalMoneyMonth(5);
    double totalMoneyMonth6 = ProductService.getInstance().getTotalMoneyMonth(6);
    double totalMoneyMonth7 = ProductService.getInstance().getTotalMoneyMonth(7);
    double totalMoneyMonth8 = ProductService.getInstance().getTotalMoneyMonth(8);
    double totalMoneyMonth9 = ProductService.getInstance().getTotalMoneyMonth(9);
    double totalMoneyMonth10 = ProductService.getInstance().getTotalMoneyMonth(10);
    double totalMoneyMonth11 = ProductService.getInstance().getTotalMoneyMonth(11);
    double totalMoneyMonth12 = ProductService.getInstance().getTotalMoneyMonth(12);

    request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
    request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
    request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
    request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
    request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
    request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
    request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
    request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
    request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
    request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
    request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
    request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);
    request.setAttribute("admin",admin);
    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/admin/monthly-revenue.jsp");
    dispatcher.forward(request, response);
  }
}
