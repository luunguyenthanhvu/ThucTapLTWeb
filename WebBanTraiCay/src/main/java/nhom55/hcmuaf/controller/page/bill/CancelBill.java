package nhom55.hcmuaf.controller.page.bill;

import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CancelBill", value = "/page/bill/cancel-bill")
public class CancelBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int idBills = Integer.valueOf(request.getParameter("idBill"));
        String status = request.getParameter("status");
       if(status.equals("daHuy")) {
           status="Đã hủy";
       }
        BillDao orderDao = new BillDaoImpl();
       orderDao.updateStatusABill(idBills,status);
       RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/bill/list-bill");
       requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
