package edu.icet.clothify.service.custom.impl;

import edu.icet.clothify.dto.Admin;
import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.AdminDao;
import edu.icet.clothify.service.custom.AdminService;
import edu.icet.clothify.util.enums.DaoType;
import org.modelmapper.ModelMapper;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean addAdmin(Admin admin) {
        AdminDao adminDao = DaoFactory.getInstance().getDao(DaoType.ADMIN);
        return adminDao.save(new ModelMapper().map(admin, AdminEntity.class));
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        AdminDao adminDao = DaoFactory.getInstance().getDao(DaoType.ADMIN);
        return adminDao.update(new ModelMapper().map(admin,AdminEntity.class));
    }

    @Override
    public Admin getAdmin(String email) {
        AdminDao adminDao = DaoFactory.getInstance().getDao(DaoType.ADMIN);
        AdminEntity admin = adminDao.getAdmin(email);
        return admin!=null?new ModelMapper().map(admin,Admin.class):null;
    }

    @Override
    public boolean updateAdmin(String email, String password) {
        AdminDao adminDao = DaoFactory.getInstance().getDao(DaoType.ADMIN);
        return adminDao.update(email,password);
    }
}
