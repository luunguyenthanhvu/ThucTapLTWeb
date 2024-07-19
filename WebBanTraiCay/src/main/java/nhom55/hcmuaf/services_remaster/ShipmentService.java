package nhom55.hcmuaf.services_remaster;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.beans_remaster.Shipments;
import nhom55.hcmuaf.dao_remaster.ShipmentDetailsDAO;
import nhom55.hcmuaf.dao_remaster.ShipmentsDAO;
import nhom55.hcmuaf.dto.request.AddNewShipmentRequestDTO;
import org.jdbi.v3.core.Handle;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentService extends AbstractService {

  private ShipmentsDAO shipmentsDAO;
  private ShipmentDetailsService shipmentDetailsService;

  public ShipmentService(Handle handle) {
    super(handle);
  }

  public void addNewShipment(AddNewShipmentRequestDTO requestDTO, Integer userId) {
    System.out.println("vào được rồi");
    long shipmentId = handle.attach(ShipmentsDAO.class)
        .insertShipment(Shipments.builder()
            .available(1)
            .dateIn(LocalDateTime.now())
            .build());

    shipmentDetailsService = new ShipmentDetailsService(handle);
    shipmentDetailsService.addNewShipmentDetails(requestDTO, (int) shipmentId, userId);
  }
}
