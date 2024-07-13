package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableResponseDTO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "APIProductList", value = "/api/get-product-list")
public class ProductList extends HttpServlet {

  private final String REQUEST_BODY = "request-body";
  private ProductService productService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    try {
      String requestDTO = (String) request.getAttribute(REQUEST_BODY);

      // convert to object
      DataTableRequestDTO dataTableRequestDTO = MyUtils.convertJsonToObject(requestDTO,
          DataTableRequestDTO.class);
      System.out.println(dataTableRequestDTO);

      productService = new ProductService();
      // start transaction
      productService.begin();
      // Lấy tổng số bản ghi
      Integer totalRecords = productService.countTotalRecords();

      // Lấy số bản ghi sau khi lọc
      Integer filteredRecords = productService.countFilteredRecords(
          dataTableRequestDTO.getSearchText(), dataTableRequestDTO.getCategory());

      // Lấy danh sách sản phẩm theo phân trang và tìm kiếm
      List<ListProductResponseDTO> productList = productService.findAllBy(
          dataTableRequestDTO.getStart(),
          dataTableRequestDTO.getLength(),
          dataTableRequestDTO.getSearchText(),
          dataTableRequestDTO.getCategory());

      DataTableResponseDTO dataTableResponse =
          DataTableResponseDTO.builder()
              .draw(dataTableRequestDTO.getDraw())
              .recordsTotal(totalRecords)
              .recordsFiltered(filteredRecords)
              .data(productList)
              .build();

      out.println(MyUtils.convertToJson(dataTableResponse));

    } catch (Exception e) {
      e.printStackTrace();
      productService.rollback();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
      out.flush();
    }
  }
}
