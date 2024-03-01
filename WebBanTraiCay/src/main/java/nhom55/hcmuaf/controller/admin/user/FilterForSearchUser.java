package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilterForSearchUser", value = "/admin/user/filter-for-search-user")
public class FilterForSearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // save url
        HttpSession session = request.getSession();
        MyUtils.setPreviousURL(session, request.getRequestURL().toString());

        String txtSearch = request.getParameter("txtSearch");
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");

        int quantity = UserService.getInstance().countResultSearchingUser(txtSearch);
//        số lượng mặc định 1 trang
        int defaultQuantityProductOnAPage = 5;
//        index user bấm vào phân trang
        String indexPage = request.getParameter("pageId");
        if (indexPage == null) {
            indexPage = "1";
        }
        int indexInitial = Integer.parseInt(indexPage);

        int indexEnd = quantity / defaultQuantityProductOnAPage;
        if (quantity % defaultQuantityProductOnAPage != 0) {
            indexEnd++;
        }
        List<Users> listSearch = UserService.getInstance().searchFilter(sortBy,order,txtSearch, indexInitial,
                defaultQuantityProductOnAPage);


        request.setAttribute("pageId",indexInitial);
        request.setAttribute("listSearch", listSearch);
        request.setAttribute("indexEnd", indexEnd);
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/admin/search-user-result.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
