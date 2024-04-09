package nhom55.hcmuaf.controller.page.bill;

import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListBills", value = "/page/bill/list-bill")
public class ListBills extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> listBills = (List<Bills>) request.getAttribute("listBills");
        if (listBills == null) {
            HttpSession session = request.getSession();
            Users users = MyUtils.getLoginedUser(session);
            BillDao orderDao = new BillDaoImpl();
            listBills = orderDao.getListBills(users.getId());

            // Tạo một bản sao của danh sách trước khi sắp xếp
            List<Bills> sortedListBills = new ArrayList<>(listBills);

            sortByOrderedDate(sortedListBills);
            request.setAttribute("listBills", sortedListBills);
            request.setAttribute("user", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/danh-sach-hoa-don.jsp");
            dispatcher.forward(request, response);
        } else {
            sortByOrderedDate(listBills);
            request.setAttribute("listBills", listBills);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/danh-sach-hoa-don.jsp");
            dispatcher.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }
    public static void sortByOrderedDate(List<Bills> listBills) {
        // Sử dụng Comparator để so sánh các đơn hàng dựa trên thời gian đặt
        Comparator<Bills> comparator = new Comparator<Bills>() {
            @Override
            public int compare(Bills bill1, Bills bill2) {
                // So sánh thời gian đặt của hai đơn hàng
                return bill2.getOrderedDate().compareTo(bill1.getOrderedDate());
            }
        };

        // Sắp xếp danh sách sử dụng Comparator đã xác định
        Collections.sort(listBills, comparator);
    }
}
