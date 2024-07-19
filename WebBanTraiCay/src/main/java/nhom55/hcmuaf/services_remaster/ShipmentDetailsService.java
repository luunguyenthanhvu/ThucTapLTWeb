package nhom55.hcmuaf.services_remaster;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.beans.enums.ETransactionType;
import nhom55.hcmuaf.beans_remaster.ShipmentDetails;
import nhom55.hcmuaf.beans_remaster.ShipmentTransactions;
import nhom55.hcmuaf.dao_remaster.ShipmentDetailsDAO;
import nhom55.hcmuaf.dao_remaster.ShipmentTransactionDAO;
import nhom55.hcmuaf.dto.request.AddNewShipmentRequestDTO;
import nhom55.hcmuaf.enums.ShipmentDetailEnums;
import org.jdbi.v3.core.Handle;

@Data
public class ShipmentDetailsService extends AbstractService {
  public ShipmentDetailsService() {

  }
  public ShipmentDetailsService(Handle handle) {
    super(handle);
  }

  public void addNewShipmentDetails(ShipmentDetails shipmentDetails) {
    handle.attach(ShipmentDetailsDAO.class).insert(shipmentDetails);
  }

  public void addNewShipmentDetails(AddNewShipmentRequestDTO requestDTO, Integer shipmentId,
      Integer userId) {
    System.out.println("vào được shipment details");
    for (var data : requestDTO.getData()) {
      var shipmentDetailId = handle.attach(ShipmentDetailsDAO.class).insert(new ShipmentDetails()
          .builder()
          .available(1)
          .quantity(data.getQuantity())
          .price(data.getImportPrice())
          .productId(data.getId())
          .shipmentsId(shipmentId)
          .note(ShipmentDetailEnums.ADD_NEW_SHIPMENT.getText())
          .build());

      handle.attach(ShipmentTransactionDAO.class)
          .insertShipmentTransaction(new ShipmentTransactions()
              .builder()
              .shipmentDetailId((int) shipmentDetailId)
              .transactionType(ETransactionType.INCREASE.getText())
              .quantity(data.getQuantity())
              .transactionDate(LocalDateTime.now())
              .note(requestDTO.getNoteText())
              .createdBy(userId)
              .build()
          );
    }
  }
}
