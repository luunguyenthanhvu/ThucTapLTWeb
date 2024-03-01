package nhom55.hcmuaf.dao;

import java.util.List;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;

public interface ProviderDao {
  List<Providers> getAllProvider();
  List<Providers> get20ProvidersForEachPage(int index, int quantityDefault);
   int countTotalRowProvidersInDatabase();
    public List<Providers> search(String search, int index, int sizePage);
    int countResultSearchingProviders(String txtSearch);
     void addNewProvider(String name, String address);
     void updateProvider(String name, String address, int idNCC);
     void deleteProvider(int idNCC);
}
