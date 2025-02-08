package edu.icet.clothify.repository.custom;

import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.repository.SuperDao;

public interface AdminDao extends SuperDao {
    boolean save(AdminEntity adminEntity);
    boolean update(AdminEntity adminEntity);
    boolean update(String email,String password);
    AdminEntity getAdmin(String email);
}
