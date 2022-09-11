package com.github.muzhaleks.dao;

import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.model.User;

import java.util.List;

public interface UserDAO {

    void registrationUser(User user)throws DAOException;

    boolean isUserByLogin(String login) throws DAOException;

    boolean isUserByEmail(String email)throws DAOException;

    User getUserByID(long id) throws DAOException;

    User getUserByLogin(String login) throws DAOException;

    User getUserBYEmail(String email) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    void changeUserPassword(long userID,String newPassword)throws DAOException;

    void blockUser(long userID)throws DAOException;

    void unblockUser(long userID)throws DAOException;

    void deleteUserFromDB(long userID) throws DAOException;
}
