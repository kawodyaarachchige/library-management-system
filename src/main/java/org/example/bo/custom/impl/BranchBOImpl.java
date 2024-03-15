package org.example.bo.custom.impl;

import org.example.bo.custom.BranchBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BranchDAO;
import org.example.dto.BranchDTO;
import org.example.entity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDaoImpl = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);

    @Override
    public boolean save(BranchDTO dto) throws SQLException, ClassNotFoundException {
        return branchDaoImpl.save(new Branch(dto.getId(),dto.getLocation(),dto.getTelephone(),dto.getEmail(),dto.getAddress(),null, null));
    }

    @Override
    public boolean update(BranchDTO dto) throws SQLException, ClassNotFoundException {
        return branchDaoImpl.update(new Branch(dto.getId(),dto.getLocation(),dto.getTelephone(),dto.getEmail(),dto.getAddress(),null,   null));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return branchDaoImpl.delete(id);
    }

    @Override
    public BranchDTO search(String id) throws SQLException, ClassNotFoundException {
        Branch search = branchDaoImpl.search(id);

        if (search == null) {
            return null;
        } else {
            return new BranchDTO(search.getId(),search.getLocation(),search.getTelephone(),search.getEmail(),search.getAddress());
        }
    }

    @Override
    public List<BranchDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Branch> all = branchDaoImpl.getAll();
        List<BranchDTO> branchDTOS = new ArrayList<>();

        if (all == null) {
            return null;
        } else {
            for (Branch branch : all) {
                branchDTOS.add(new BranchDTO(branch.getId(),branch.getLocation(),branch.getTelephone(),branch.getEmail(),branch.getAddress()));
            }
            return branchDTOS;
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return branchDaoImpl.generateNextId();
    }

    @Override
    public BranchDTO searchByLocation(String location) throws SQLException, ClassNotFoundException{
        Branch branch = branchDaoImpl.searchByLocation(location);
        return new BranchDTO(branch.getId(),branch.getLocation(),branch.getTelephone(),branch.getEmail(),branch.getAddress());
    }

}
