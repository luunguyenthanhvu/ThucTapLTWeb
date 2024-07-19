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
import nhom55.hcmuaf.dto.request.PageableProductRequestDTO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.dto.response.ListProductShopResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "APIProductList", value = "/api/product-list/*")
public class ProductAPI extends HttpServlet {

  private final String REQUEST_BODY = "request-body";
  private ProductService productService;
  private final Integer MAX_RECORD_PAGING_PRODUCT = 20;

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
      String context = request.getPathInfo();
      String requestDTO = (String) request.getAttribute(REQUEST_BODY);
      productService = new ProductService();
      productService.begin();
      switch (context) {
        case "/get-data-table":
          getListResponseDataTable(requestDTO, out);
          break;
        case "/get-shop-item":
          getProductItemPaging(requestDTO, out);
          break;
      }


    } catch (Exception e) {
      e.printStackTrace();
      productService.rollback();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
      out.flush();
    }
  }

  private void getListResponseDataTable(String requestDTO, PrintWriter out) {
    // convert to object
    DataTableRequestDTO dataTableRequestDTO = MyUtils.convertJsonToObject(requestDTO,
        DataTableRequestDTO.class);
    System.out.println(dataTableRequestDTO);
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
        dataTableRequestDTO.getCategory(),
        dataTableRequestDTO.getOrder());

    DataTableResponseDTO dataTableResponse =
        DataTableResponseDTO.builder()
            .draw(dataTableRequestDTO.getDraw())
            .recordsTotal(totalRecords)
            .recordsFiltered(filteredRecords)
            .data(productList)
            .build();

    out.println(MyUtils.convertToJson(dataTableResponse));
  }

  private void getProductItemPaging(String requestDTO, PrintWriter out) {
    PageableProductRequestDTO pageableProductRequestDTO = MyUtils.convertJsonToObject(requestDTO,
        PageableProductRequestDTO.class);
    // Lấy tổng số bản ghi
//    Integer totalRecords = productService.countTotalRecords();

    // Lấy số bản ghi sau khi lọc
//    Integer filteredRecords = productService.countFilteredRecords(
//        dataTableRequestDTO.getSearchText(), dataTableRequestDTO.getCategory());

    // Lấy danh sách sản phẩm theo phân trang và tìm kiếm
    List<ListProductShopResponseDTO> productList = productService.findAllBy(
        pageableProductRequestDTO.getStart(),
        MAX_RECORD_PAGING_PRODUCT,
        pageableProductRequestDTO.getSearchText(),
        pageableProductRequestDTO.getCategory()
    );
    System.out.println(productList);

    out.println(MyUtils.convertToJson(productList));
  }
}
