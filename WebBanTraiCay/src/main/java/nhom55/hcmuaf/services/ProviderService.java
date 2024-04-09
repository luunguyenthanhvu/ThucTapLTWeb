package nhom55.hcmuaf.services;

import java.util.List;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.dao.ProviderDao;
import nhom55.hcmuaf.dao.daoimpl.ProviderDaoImpl;

public class ProviderService {
  private static ProviderService instance;
  private ProviderDao providerDao;
  public ProviderService() {
    providerDao = new ProviderDaoImpl();
  }
  public static ProviderService getInstance() {
    if(instance == null) {
      instance = new ProviderService();
    }
    return instance;
  }
  public List<Providers> getAll() {
    return providerDao.getAllProvider();
  }
  public List<Providers> get20ProvidersForEachPage(int index, int quantityDefault) {
    return providerDao.get20ProvidersForEachPage(index,quantityDefault);
  }
  public int countTotalRowProvidersInDatabase() {
    return providerDao.countTotalRowProvidersInDatabase();
  }
  public List<Providers> search(String search, int index, int sizePage) {
    return providerDao.search(search,index,sizePage);
  }
  public int countResultSearchingProviders(String txtSearch) {
    return providerDao.countResultSearchingProviders(txtSearch);
  }
  public void addNewProvider(String name, String address) {
     providerDao.addNewProvider(name,address);
  }
  public void updateProvider(String name, String address, int idNCC) {
    providerDao.updateProvider(name,address,idNCC);
  }
  public void deleteProvider(int idNCC) {
    providerDao.deleteProvider(idNCC);
  }
}
