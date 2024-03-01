package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilterForAllUser", value = "/admin/user/filter-for-all-user")

public class FilterForAllUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // save url
        HttpSession session = request.getSession();
        MyUtils.setPreviousURL(session, request.getRequestURL().toString());
//       Lấy các thuộc tính của filter
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");

        // pageSTR(pageNumber là kiểu int khi chuyển từ String PageSTr),  là số mà người dùng bấm vào số mà muốn chuyển trang
        String pageStr = request.getParameter("pageId");
        int pageNumber = 0;
        if(pageStr == null) {
            pageNumber =1;
        } else {
            pageNumber = Integer.valueOf(pageStr);
        }

        int quantityDefault =5;
        int totalRow = UserService.getInstance().countTotalUserInDatabase();
        int haveMaxPage = (totalRow/quantityDefault) +1;

        List<Users> list =  UserService.getInstance().sortByFilter(pageNumber,quantityDefault,sortBy,order);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/user-list.jsp");
        request.setAttribute("sortBy",sortBy);
        request.setAttribute("order",order);
        request.setAttribute("haveMaxPage",haveMaxPage);
        request.setAttribute("listOfUser",list);
        request.setAttribute("pageId",pageNumber);
        rd.forward(request,response);
    }
}
