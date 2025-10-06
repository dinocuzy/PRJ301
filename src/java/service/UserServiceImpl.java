/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.User;
import userDao.IUserDAO;
import userDao.UserDAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UserServiceImpl implements UserService {

    private IUserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAO();  
    }

    @Override
    public void insertUser(User user) throws SQLException {
        userDAO.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return userDAO.selectUser(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return userDAO.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return userDAO.updateUser(user);
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userDAO.searchUsers(keyword);
    }
}
