package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Admin;
import edu.icet.clothify.service.SupperService;

public interface AdminService extends SupperService {
    boolean addAdmin(Admin admin);
    boolean updateAdmin(String email,String password);
    Admin getAdmin(String email);
}
