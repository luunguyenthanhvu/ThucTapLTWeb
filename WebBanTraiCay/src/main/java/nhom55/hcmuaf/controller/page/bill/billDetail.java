package nhom55.hcmuaf.controller.page.bill;

import nhom55.hcmuaf.beans.BillDetails;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "billDetail", value = "/page/bill/detail")
public class billDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBill = Integer.valueOf(request.getParameter("idBills"));
        BillDao orderDao = new BillDaoImpl();
        List<BillDetails> list = orderDao.getListProductInABill(idBill);
        request.setAttribute("list",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/chi-tiet-hoa-don.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
