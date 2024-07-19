package nhom55.hcmuaf.services_remaster;

import nhom55.hcmuaf.beans_remaster.ShipmentDetails;
import nhom55.hcmuaf.enums.ShipmentDetailEnums;

public class Test {

  public static void main(String[] args) {
    ShipmentDetailsService shipmentDetailsService = new ShipmentDetailsService();
    shipmentDetailsService.begin();
    shipmentDetailsService.addNewShipmentDetails(new ShipmentDetails()
        .builder()
        .available(1)
        .quantity(20)
        .price(20000.0)
        .productId(1)
        .note(ShipmentDetailEnums.ADD_NEW_SHIPMENT.getText())
        .build());
    shipmentDetailsService.save();
  }
}
