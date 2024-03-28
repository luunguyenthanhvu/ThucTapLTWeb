package nhom55.hcmuaf.controller.admin.provider;

import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateProvider", value = "/admin/provider/update-provider")
public class UpdateProvider extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
          int idProvider = Integer.valueOf(request.getParameter("id"));
        List<Providers> list = ProviderService.getInstance().getAll();
        Providers provider =null;
        for(Providers p: list) {
            if(p.getId() == idProvider) {
                provider =p;
                break;
            }
        }
              request.setAttribute("admin", admin);
             request.setAttribute("provider",provider);
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/update-provider.jsp");
             dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
