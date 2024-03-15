package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.entity.BorrowingBooks;
import org.example.entity.Branch;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDaoImpl = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        BranchDTO branch = dto.getBranch();
        Branch branch1 = new Branch(branch.getId(), branch.getLocation(), branch.getTelephone(), branch.getEmail(), branch.getAddress(),null,null);
        List<BorrowingBooks> borrow = new ArrayList<>();
        return userDaoImpl.save(new User(dto.getName(), dto.getEmail(), dto.getPassword(),dto.getTelephone(), branch1,borrow));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        BranchDTO branch = dto.getBranch();
        List<BorrowingBooks> borrow = new ArrayList<>();
        Branch branch1 = new Branch(branch.getId(), branch.getLocation(), branch.getTelephone(), branch.getEmail(), branch.getAddress(),null,null);
        return userDaoImpl.update(new User(dto.getName(), dto.getEmail(), dto.getPassword(),dto.getTelephone(), branch1,borrow));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDaoImpl.delete(id);
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        User search = userDaoImpl.search(id);

        if (search == null){
            return null;
        } else {
            return new UserDTO(search.getName(), search.getEmail(), search.getPassword(),search.getTelephone(), new BranchDTO(search.getBranch().getId(), search.getBranch().getLocation(), search.getBranch().getTelephone(), search.getBranch().getEmail(), search.getBranch().getAddress()));
        }
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        List<User> all = userDaoImpl.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        if (all == null) {
            return null;
        } else {
            for (User user : all) {
                userDTOS.add(new UserDTO(user.getName(), user.getEmail(), user.getPassword(),user.getTelephone(), new BranchDTO(user.getBranch().getId(), user.getBranch().getLocation(), user.getBranch().getTelephone(), user.getBranch().getEmail(), user.getBranch().getAddress())));
            }
            return userDTOS;
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return userDaoImpl.generateNextId();
    }

    @Override
    public List<UserDTO> searchUserByName(String name) throws SQLException, ClassNotFoundException {
        List<User> userDTOS = userDaoImpl.searchUserByName(name);
        List<UserDTO> userDTOS1 = new ArrayList<>();

        for (User user: userDTOS) {
            UserDTO dto = new UserDTO(user.getEmail(),user.getName(),user.getPassword(),user.getTelephone(),new BranchDTO(user.getBranch().getId(),user.getBranch().getLocation(),user.getBranch().getTelephone(),user.getBranch().getEmail(),user.getBranch().getAddress()));
            userDTOS1.add(dto);
        }

        return userDTOS1;
    }

    @Override
    public UserDTO searchUserByEmail(String email) throws SQLException, ClassNotFoundException {
        User user = userDaoImpl.searchUserByEmail(email);
        return new UserDTO(user.getEmail(),user.getName(),user.getPassword(),user.getTelephone(),new BranchDTO(user.getBranch().getId(),user.getBranch().getLocation(),user.getBranch().getTelephone(),user.getBranch().getEmail(),user.getBranch().getAddress()));
    }

    @Override
    public UserDTO searchUserByTelephone(String telephone) throws SQLException, ClassNotFoundException {
        User user = userDaoImpl.searchUserByTelephone(telephone);
        return new UserDTO(user.getEmail(),user.getName(),user.getPassword(),user.getTelephone(),new BranchDTO(user.getBranch().getId(),user.getBranch().getLocation(),user.getBranch().getTelephone(),user.getBranch().getEmail(),user.getBranch().getAddress()));
    }
}
