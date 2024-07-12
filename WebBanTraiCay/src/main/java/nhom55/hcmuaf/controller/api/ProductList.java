package nhom55.hcmuaf.controller.api;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableResponse;
import nhom55.hcmuaf.dto.response.MessageResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(name = "APIProductList", value = "/api/get-product-list")
public class ProductList extends HttpServlet {

  private ProductService productService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();

      // Lấy request body và chuyển sang object Java
      ObjectMapper mapper = new ObjectMapper();
      MessageResponseDTO messageResponseDTO = mapper.readValue(request.getReader(),
          MessageResponseDTO.class);
      System.out.println("cc lang coc");
      System.out.println(messageResponseDTO);
      productService = new ProductService();
      // start transaction
      productService.begin();
      var list = productService.findAllBy();
      DataTableResponse dataTableResponse =
          DataTableResponse.builder()
              .draw(10)
              .recordsTotal(1000)
              .recordsFiltered(800)
              .data(list)
              .build();
      out.println(MyUtils.convertToJson(dataTableResponse));
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
      productService.rollback();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      String requestDTO = (String) request.getAttribute("requestBody");
      System.out.println(requestDTO);
      // Lấy request body và chuyển sang object Java
      Gson gson = new Gson();
      DataTableRequestDTO dataTableRequestDTO = gson.fromJson(requestDTO,
          DataTableRequestDTO.class);
      System.out.println(dataTableRequestDTO);
      System.out.println("cc lang coc");
      System.out.println(dataTableRequestDTO);
      productService = new ProductService();
      // start transaction
      productService.begin();
      var list = productService.findAllBy();
      var newList = new ArrayList<>();
      for (int i = dataTableRequestDTO.getStart(); i < dataTableRequestDTO.getStart() + 10; i++) {
        newList.add(list.get(i));
      }
      DataTableResponse dataTableResponse =
          DataTableResponse.builder()
              .draw(dataTableRequestDTO.getDraw() + 1)
              .recordsTotal(1000)
              .recordsFiltered(800)
              .data(newList)
              .build();
      out.println(MyUtils.convertToJson(dataTableResponse));
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
      productService.rollback();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
    }
  }
}
