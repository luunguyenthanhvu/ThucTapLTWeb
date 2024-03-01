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

@WebServlet(name = "ListUserForward", value = "/admin/user/list-user-forward")

public class ListUserForward extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // save url
        HttpSession session = request.getSession();
        MyUtils.setPreviousURL(session, request.getRequestURL().toString());

        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        String pageStr = request.getParameter("pageId");

//        Sẽ có 2 trường hợp. 1 là khi vừa mới load vô trang Shop thì sortBy và order sẽ null. 2 là khi người chuyển sang
//        trang mới nhưng chưa chọn bất kì lựa chọn filter nào thì sortBy và order sẽ nhận giá trị "" do Tomcat
        if(sortBy == null && order==null || ("".equals(sortBy) && "".equals(order))) {

            int pageNumber = 0;
            if(pageStr == null) {
                pageNumber =1;
            } else {
                pageNumber = Integer.valueOf(pageStr);
            }

            int quantityDefault =5;
            int totalRow = UserService.getInstance().countTotalUserInDatabase();
            int haveMaxPage = (totalRow/quantityDefault) +1;
            List<Users> list = UserService.getInstance().get5UsersForEachPage(pageNumber,quantityDefault);

            Users admin = MyUtils.getLoginedUser(session);
            request.setAttribute("admin", admin);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/user-list.jsp");
            request.setAttribute("haveMaxPage",haveMaxPage);
            request.setAttribute("listOfUser",list);
            request.setAttribute("pageId",pageNumber);
            rd.forward(request,response);
        }
//        Đây là trường hợp người dùng đã chọn Filter sẽ đưa FilterForAllUser xử lý
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/filter-for-search-user");
            request.setAttribute("pageId",pageStr);
            request.setAttribute("sortBy",sortBy);
            request.setAttribute("order",order);
            dispatcher.forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
