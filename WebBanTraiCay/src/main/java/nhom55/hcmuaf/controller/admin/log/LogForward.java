package nhom55.hcmuaf.controller.admin.log;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.daoimpl.LogDaoImpl;
import nhom55.hcmuaf.log.Log;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LogForward", value = "/admin/log/log-center")
public class LogForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/manage-log.jsp");
        LogDaoImpl<Users> logDao = new LogDaoImpl<>();
        request.setAttribute("listLog", logDao.show());
         dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String arrayIdLog = request.getParameter("arrayIdLog");
        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(arrayIdLog);
            LogDaoImpl<Users> logDao = new LogDaoImpl<>();
            List<String> list = new ArrayList<>();
            for(int i = 0; i < jsonArray.size(); i++){
                list.add(jsonArray.get(i).toString());
            }
            for(int i = 0; i < list.size(); i++){
                logDao.delete(Integer.valueOf(list.get(i)));
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter()
                    .write("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}