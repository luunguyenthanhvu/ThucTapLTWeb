package nhom55.hcmuaf.controller.admin.log;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.daoimpl.LogDaoImpl;
import nhom55.hcmuaf.log.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogController", value = "/admin/log/manage-log")
public class LogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        LogDaoImpl<Users> logDao = new LogDaoImpl<>();
        JSONArray jsonArray = new JSONArray();
        for(Log log: logDao.show()){
            JSONObject userObj = new JSONObject();
            userObj.put("id", log.getId());
            userObj.put("ip", log.getIp());
            userObj.put("level", String.valueOf(log.getLevel()));
            userObj.put("address", log.getAddress());
            userObj.put("note", log.getNote());
            userObj.put("national", log.getNational());
            userObj.put("preValue", String.valueOf(log.getPreValue()));
            userObj.put("currentValue", String.valueOf(log.getCurrentValue()));
            userObj.put("createAt", String.valueOf(log.getCreateAt()));
            userObj.put("updateAt", String.valueOf(log.getUpdateAt()));
            jsonArray.add(userObj);

        }
        JSONObject result = new JSONObject();
        result.put("data", jsonArray);
        out.print(result);
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}