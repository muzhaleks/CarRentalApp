package com.github.muzhaleks.services;

import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.model.User;

import java.util.List;

public interface UserService {
    List<User> geaAllUsers() throws UserServiceException;
    User signIn(String login, String password) throws UserServiceException;
    void createUser(String login, String password, String firstName, String lastName, String passportSerialNumber,
                    String driverLicenceNumber, String email, String phoneNumber) throws UserServiceException;
    User getUserByLogin(String login) throws UserServiceException;
    User getUserByEmail(String email) throws UserServiceException;
    User getUserById(long userID) throws UserServiceException;
    void deleteUser (long userID)throws UserServiceException;
    void changePassword(long userID, String oldPassword, String newPassword, String repeatPassword)throws UserServiceException;
    void blockUser(long userID) throws UserServiceException;
    void unblockUser(long userID) throws UserServiceException;
}
