package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO  {
    AdminDAO adminDao = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDao.save(new Admin(dto.getEmail(), dto.getName(),dto.getTelephone(),dto.getPassword()));
    }

    @Override
    public boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDao.update(new Admin(dto.getName(),dto.getEmail(),dto.getTelephone(),dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return adminDao.delete(id);
    }

    @Override
    public AdminDTO search(String id) throws SQLException, ClassNotFoundException {
        Admin search = adminDao.search(id);

        if (search == null) {
            return null;
        } else {
            return new AdminDTO(search.getName(),search.getEmail(),search.getTelephone(),search.getPassword());
        }
    }

    @Override
    public List<AdminDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Admin> all = adminDao.getAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();

        if (all == null) {
            return null;
        }else {
            for (Admin admin : all) {
                adminDTOS.add(new AdminDTO(admin.getName(),admin.getEmail(),admin.getTelephone(),admin.getPassword()));
            }
            return adminDTOS;
        }

    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return adminDao.generateNextId();
    }

    @Override
    public boolean updatePassword(String username, String password) throws ClassNotFoundException {
        return adminDao.updatePassword(username,password);
    }

}
