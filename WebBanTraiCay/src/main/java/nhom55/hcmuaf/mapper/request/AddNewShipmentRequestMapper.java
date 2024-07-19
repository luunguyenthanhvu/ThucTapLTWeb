package nhom55.hcmuaf.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddNewShipmentRequestMapper {

  AddNewShipmentRequestMapper INSTANCE = Mappers.getMapper(AddNewShipmentRequestMapper.class);
}
