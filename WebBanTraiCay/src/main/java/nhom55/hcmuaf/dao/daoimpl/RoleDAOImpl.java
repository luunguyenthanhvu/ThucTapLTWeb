package nhom55.hcmuaf.dao.daoimpl;

import nhom55.hcmuaf.beans.Role;
import nhom55.hcmuaf.dao.RoleDAO;
import nhom55.hcmuaf.database.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public List<Role> getAllRoles() {
        return JDBIConnector.get().withHandle(h -> h.createQuery("Select * from roles").mapToBean(Role.class).stream().collect(Collectors.toList()));
    }
}
