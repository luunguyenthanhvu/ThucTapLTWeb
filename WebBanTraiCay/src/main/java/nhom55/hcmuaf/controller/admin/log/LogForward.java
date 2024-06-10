package nhom55.hcmuaf.controller.admin.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogForward", value = "/admin/log/log-center")
public class LogForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/manage-log.jsp");
         dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}