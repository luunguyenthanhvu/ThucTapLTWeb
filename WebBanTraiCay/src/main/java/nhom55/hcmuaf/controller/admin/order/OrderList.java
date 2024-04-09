package nhom55.hcmuaf.controller.admin.order;

import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderList", value = "/admin/order/order-list")
public class OrderList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        BillDao billDao = new BillDaoImpl();
        String pageStr = request.getParameter("pageId");
        int pageNumber = 0;
        if(pageStr == null) {
            pageNumber =1;
        } else {
            pageNumber = Integer.valueOf(pageStr);
        }

        int quantityDefault =10;
        int totalRow = billDao.countTotalRowProductInDatabase();
        int haveMaxPage = (totalRow/quantityDefault) +1;
        List<Bills> listBills = billDao.get10BillsForEachPage(pageNumber,quantityDefault);
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/admin/order-list.jsp");
        request.setAttribute("listBills",listBills);
        request.setAttribute("admin", admin);
        request.setAttribute("haveMaxPage",haveMaxPage);
        request.setAttribute("pageId",pageNumber);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doGet(request,response);
    }
}
