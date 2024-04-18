package nhom55.hcmuaf.controller.admin.order;


import nhom55.hcmuaf.beans.BillDetails;
import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.*;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateOrder", value = "/admin/provider/updateOrder")
public class UpdateOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBill = Integer.valueOf(request.getParameter("idOrder"));
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        BillDao orderDao = new BillDaoImpl();
        UsersDao usersDao = new UsersDaoImpl();
        List<BillDetails> detailList = orderDao.getListProductInABill(idBill);
        Bills bill = orderDao.getABill(idBill);
        int idUser = orderDao.getIdUser(idBill);
        Users users = null;
        for(Users u: usersDao.showInfoUser() ) {
            if(u.getId() == idUser) {
                users = u;
                break;
            }
        }
        request.setAttribute("user", users);
        request.setAttribute("admin", admin);
        request.setAttribute("bill",bill);
        request.setAttribute("detailList",detailList);
        request.setAttribute("idBill",idBill);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/update-order.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBill = Integer.valueOf(request.getParameter("idBill"));
        String status = request.getParameter("selectedStatus");
        BillDao billDao = new BillDaoImpl();
        billDao.updateStatusABill(idBill,status);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/order/order-list");
        dispatcher.forward(request,response);
    }
}
