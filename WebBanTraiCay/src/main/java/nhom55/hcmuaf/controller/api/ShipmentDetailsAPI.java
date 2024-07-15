package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableResponseDTO;
import nhom55.hcmuaf.dto.response.MessageResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ShipmentDetailsAPIServlet", value = "/api/shipments-api/*")
public class ShipmentDetailsAPI extends HttpServlet {

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
      String context = request.getPathInfo();
      String requestDTO = (String) request.getAttribute(REQUEST_BODY);
      switch (context) {
        case "/add-new-shipments":
          List<Integer> selectedProducts = MyUtils.convertJsonToObject(requestDTO, List.class);
          request.getSession().setAttribute("listProductShipments", selectedProducts);
          out.println(
              MyUtils.convertToJson(MessageResponseDTO.builder().message("Success").build()));
          break;
        case "/list-item-shipments":
          productService = new ProductService();
          productService.begin();
          var responseDTO = getListItemForShipments(request, response, requestDTO);
          out.println(MyUtils.convertToJson(responseDTO));
          break;
      }
    } catch (Exception e) {
      productService.rollback();
      e.printStackTrace();
      throw new MyHandleException("Loi server", 500);
    } finally {
      productService.close();
      out.flush();
    }
  }

  private DataTableResponseDTO getListItemForShipments(HttpServletRequest request,
      HttpServletResponse response,
      String requestDTO) {
    List<Integer> productList = (List<Integer>) request.getSession()
        .getAttribute("listProductShipments");
    if (productList.isEmpty()) {
      productList = new ArrayList<>();
    }
    System.out.println("danh sach san pham");
    System.out.println(productList);

    // convert to object
    DataTableRequestDTO dataTableRequestDTO = MyUtils.convertJsonToObject(requestDTO,
        DataTableRequestDTO.class);
    System.out.println(dataTableRequestDTO);

    // get data
    var data = productService.findListProductByProductId(productList);

    return new DataTableResponseDTO()
        .builder()
        .draw(dataTableRequestDTO.getDraw())
        .data(data)
        .recordsTotal(data.size())
        .recordsFiltered(data.size())
        .build();
  }
}
