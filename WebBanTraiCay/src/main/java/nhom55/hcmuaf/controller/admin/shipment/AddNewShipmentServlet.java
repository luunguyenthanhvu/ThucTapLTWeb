package nhom55.hcmuaf.controller.admin.shipment;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddNewShipmentServlet", value = "/admin/shipment/*")
public class AddNewShipmentServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String context = request.getPathInfo();
    switch (context) {
      case "/add-new-shipments":
        RequestDispatcher dispatcher = this.getServletContext()
            .getRequestDispatcher("/WEB-INF/admin/shipment/add-new-shipment.jsp");
        dispatcher.forward(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/admin/shipment/add-new-shipment.jsp");
    dispatcher.forward(request, response);
  }
}
