package nhom55.hcmuaf.controller.admin.log;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.daoimpl.LogDaoImpl;
import nhom55.hcmuaf.dto.data_table.CustomDataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableResponseDTO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.LogDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "LogController", value = "/api/admin/log/manage-log")
public class LogController extends HttpServlet {
    private final String REQUEST_BODY = "request-body";
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String requestDTO = (String) request.getAttribute(REQUEST_BODY);

            // convert to object
            CustomDataTableRequestDTO dataTableRequestDTO = MyUtils.convertJsonToObject(requestDTO,
                    CustomDataTableRequestDTO.class);
            System.out.println(dataTableRequestDTO);

            LogDaoImpl<Users> logDao = new LogDaoImpl<>();
            int totalRecords = logDao.countTotalRecords();
            int filteredRecords = logDao.countFilteredRecords(dataTableRequestDTO.getSearchText(), dataTableRequestDTO.getCategory());
            List<Log> logList = logDao.filter(dataTableRequestDTO.getStart(), dataTableRequestDTO.getLength(), dataTableRequestDTO.getSearchText(), dataTableRequestDTO.getCategory());
            List<LogDTO> logDTOList = convertToLogDTOList(logList);
            DataTableResponseDTO dataTableResponse =
                    DataTableResponseDTO.builder()
                            .draw(dataTableRequestDTO.getDraw())
                            .recordsTotal(totalRecords)
                            .recordsFiltered(filteredRecords)
                            .data(logDTOList)
                            .build();

            out.println(MyUtils.convertToJson(dataTableResponse));

        } catch (Exception e) {
            e.printStackTrace();

            throw new MyHandleException("Loi server", 500);
        } finally {

            out.flush();
        }
    }
    public List<LogDTO> convertToLogDTOList(List<Log> logList) {
        return logList.stream().map(LogDTO::new).collect(Collectors.toList());
    }
}