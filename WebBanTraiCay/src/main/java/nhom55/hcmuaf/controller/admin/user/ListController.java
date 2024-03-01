package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ListController", value = "/admin/user/list-controller")

public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // save url
        HttpSession session = request.getSession();
        MyUtils.setPreviousURL(session, request.getRequestURL().toString());
        Users admin = MyUtils.getLoginedUser(session);

        String txtSearch = request.getParameter("txtSearch");
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");

        int quantity = UserService.getInstance().countResultSearchingUser(txtSearch);
//        số lượng mặc định 1 trang
        int defaultQuantityUserOnAPage = 5;
//        index user bấm vào phân trang
        String indexPage = request.getParameter("index");

//    int indexInitial  = 1;
//    if(indexPage != null) {
//      indexInitial = Integer.parseInt(indexPage);
//    }
        int indexInitial = Integer.parseInt(indexPage);

        int indexEnd = quantity / defaultQuantityUserOnAPage;

        if (quantity % defaultQuantityUserOnAPage != 0) {
            indexEnd++;
        }
        List<Users> listSearch = null;
        if(sortBy == null && order==null || ("".equals(sortBy) && "".equals(order))) {
            listSearch = UserService.getInstance().search(txtSearch, indexInitial,
                    defaultQuantityUserOnAPage);
            request.setAttribute("sortBy","");
            request.setAttribute("order","");
            request.setAttribute("pageId",indexInitial);
            request.setAttribute("listSearch", listSearch);
            request.setAttribute("indexEnd", indexEnd);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/WEB-INF/admin/search-user-result.jsp").forward(request, response);
        }
    }
}
