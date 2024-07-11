package nhom55.hcmuaf.services_remaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.dao_remaster.ProvidersDAO;
import org.jdbi.v3.core.Handle;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderService extends AbstractService {

  private ProvidersDAO providersDAO;

  public ProviderService(Handle handle) {
    super(handle);
  }

  public String getProviderNameById(int id) {
    providersDAO = this.handle.attach(ProvidersDAO.class);
    return providersDAO.findProviderNameById(id).getProviderName();
  }
}
