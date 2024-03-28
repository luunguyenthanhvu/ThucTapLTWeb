package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserList", value = "/admin/user/user-list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("pageId");
        int pageNumber = 0;
        if(pageStr == null) {
            pageNumber =1;
        } else {
            pageNumber = Integer.valueOf(pageStr);
        }

        int quantityDefault = 5;
        int totalRow = UserService.getInstance().countTotalUserInDatabase();
        int haveMaxPage = (totalRow/quantityDefault) +1;
        List<Users> listUser = UserService.getInstance().get5UsersForEachPage(pageNumber,quantityDefault);

        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);

        request.setAttribute("admin", admin);
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/admin/user-list.jsp");
        request.setAttribute("listOfUser",listUser);
        request.setAttribute("haveMaxPage",haveMaxPage);
        request.setAttribute("pageId",pageNumber);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
