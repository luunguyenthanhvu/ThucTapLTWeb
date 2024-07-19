package nhom55.hcmuaf.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO;
import nhom55.hcmuaf.dto.data_table.DataTableRequestDTO.OrderDTO;
import nhom55.hcmuaf.dto.data_table.DataTableResponseDTO;
import nhom55.hcmuaf.dto.request.AddNewShipmentRequestDTO;
import nhom55.hcmuaf.dto.request.ProductDetailsRequestDTO;
import nhom55.hcmuaf.dto.response.ListProductResponseDTO;
import nhom55.hcmuaf.dto.response.MessageResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.services_remaster.ShipmentService;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "ShipmentDetailsAPIServlet", value = "/api/shipments-api/*")
public class ShipmentDetailsAPI extends HttpServlet {

  private static final Map<Integer, String> mapRecordDataTable = Map.of(
      0, "productName",
      1, "id",
      2, "provider",
      3, "quantityStock",
      4, "id",
      5, "id",
      6, "id",
      7, "id"
  );

  private final String REQUEST_BODY = "request-body";
  private ProductService productService;
  private ShipmentService shipmentService;

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
        case "/delete-item-shipments":
          List<Integer> productList = (List<Integer>) request.getSession()
              .getAttribute("listProductShipments");
          ProductDetailsRequestDTO productDetailsRequestDTO = MyUtils.convertJsonToObject(
              requestDTO, ProductDetailsRequestDTO.class);

          productList.removeIf(i -> productDetailsRequestDTO.getId().equals(i));
          request.setAttribute("listProductShipments", productList);
          out.println(MyUtils.convertToJson(MessageResponseDTO.builder().message("Tuan ga vkl")));
          break;
        case "/create-new-shipment":
          productService = new ProductService();
          productService.begin();
          shipmentService = new ShipmentService(productService.getHandle());
          System.out.println("đã handle");
          shipmentService.addNewShipment(MyUtils.convertJsonToObject(requestDTO,
                  AddNewShipmentRequestDTO.class),
              MyUtils.getLoginedUser(request.getSession()).getId());
          productService.save();
          out.println(MyUtils.convertToJson(MessageResponseDTO.builder().message("Tuan ga vkl")));
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

    // convert to object
    DataTableRequestDTO dataTableRequestDTO = MyUtils.convertJsonToObject(requestDTO,
        DataTableRequestDTO.class);

    // get data
    List<ListProductResponseDTO> data = productService.findListProductByProductId(productList);

    // Apply sorting and pagination
    var sortedData = paginateAndSortProductsList(data, dataTableRequestDTO);

    Integer filterRecord = sortedData.size();
    if (dataTableRequestDTO.getSearchText() == null || dataTableRequestDTO.getSearchText()
        .isEmpty()) {
      filterRecord = data.size();
    }

    return new DataTableResponseDTO()
        .builder()
        .draw(dataTableRequestDTO.getDraw())
        .data(sortedData)
        .recordsTotal(data.size())
        .recordsFiltered(filterRecord)
        .build();
  }

  private List<ListProductResponseDTO> paginateAndSortProductsList(
      List<ListProductResponseDTO> productsList, DataTableRequestDTO dataTableRequestDTO) {
    // Pagination info
    Integer start = dataTableRequestDTO.getStart();
    Integer length = dataTableRequestDTO.getLength();
    int startIndex = start != null ? start : 0;
    int endIndex = start != null && length != null ? start + length : productsList.size();

    // Search text
    String searchText = dataTableRequestDTO.getSearchText();

    // Sorting
    List<OrderDTO> orders = dataTableRequestDTO.getOrder();
    Comparator<ListProductResponseDTO> comparator = createProductComparator(orders);

    // Apply filtering and sorting
    List<ListProductResponseDTO> filteredAndSorted = productsList.stream()
        .filter(product -> matchSearchCriteria(product, searchText))
        .sorted(comparator)
        .collect(Collectors.toList());

    // Pagination
    filteredAndSorted = filteredAndSorted.stream()
        .skip(startIndex)
        .limit(endIndex - startIndex)
        .collect(Collectors.toList());

    return filteredAndSorted;
  }

  private Comparator<ListProductResponseDTO> createProductComparator(List<OrderDTO> orders) {
    if (orders == null || orders.isEmpty()) {
      // Default sorting by id ASC if no order specified
      return Comparator.comparing(ListProductResponseDTO::getId);
    }

    OrderDTO orderDTO = orders.get(0); // Assuming only one column is sorted

    // Retrieve sort field and direction
    String sortField = mapRecordDataTable.get(orderDTO.getColumn());
    String sortOrder = orderDTO.getDir(); // ASC or DESC

    // Create comparator based on sortField and sortOrder
    Comparator<ListProductResponseDTO> comparator = null;
    switch (sortField) {
      case "productName":
        comparator = Comparator.comparing(ListProductResponseDTO::getProductName);
        break;
      case "id":
        comparator = Comparator.comparing(ListProductResponseDTO::getId, Comparator.naturalOrder());
        break;
      case "provider":
        comparator = Comparator.comparing(ListProductResponseDTO::getProvider);
        break;
      case "quantityStock":
        comparator = Comparator.comparing(ListProductResponseDTO::getQuantityStock,
            Comparator.naturalOrder());
        break;
      default:
        comparator = Comparator.comparing(ListProductResponseDTO::getId, Comparator.naturalOrder());
        break;
    }

    // Apply DESC ordering if specified
    if ("DESC".equalsIgnoreCase(sortOrder)) {
      comparator = comparator.reversed();
    }

    return comparator;
  }

  private boolean matchSearchCriteria(ListProductResponseDTO product, String searchText) {
    if (searchText == null || searchText.isEmpty()) {
      return true;
    }

    String lowerCaseSearchText = searchText.toLowerCase();

    if (product.getProductName().toLowerCase().contains(lowerCaseSearchText)) {
      return true;
    }

    return false;
  }
}
