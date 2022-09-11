package com.github.muzhaleks.services.impl;

import com.github.muzhaleks.dao.UserDAO;
import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.factory.DAOFactory;
import com.github.muzhaleks.model.User;
import com.github.muzhaleks.services.UserService;
import com.github.muzhaleks.validator.LoginPasswordValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private UserDAO userDAO = DAOFactory.INSTANCE.getUserDao();

    @Override
    public List<User> geaAllUsers() throws UserServiceException {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e) {
            };
        }
    }

    @Override
    public User signIn(String login, String password) throws UserServiceException {
        User user = null;

        if (LoginPasswordValidator.validationLogin(login) && LoginPasswordValidator.validationPassword(password)) {
            try {
                user = userDAO.getUserByLogin(login);

            } catch (DAOException e) {
                LOGGER.warn(e);
                throw new UserServiceException(e) {
                };
            }
            if (user.getLogin() != null && user.isActiveStatus()) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public void createUser(String login, String password, String firstName, String lastName, String passportSerialNumber,
                           String driverLicenceNumber, String email, String phoneNumber) throws UserServiceException {
        try {
            if (userDAO.isUserByLogin(login)) {
                throw new UserServiceException("Login is used");
            }
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }

        try {
            if (userDAO.isUserByEmail(email)) {
                throw new UserServiceException("Email is used");
            }
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }

        try {
            User newUser = buildTempUser(login, password, firstName, lastName, passportSerialNumber, driverLicenceNumber,
                    email, phoneNumber);
            userDAO.registrationUser(newUser);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws UserServiceException {
        User user = null;
        try {
            user = userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e) {
            };
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(long id) throws UserServiceException {
        User user = null;
        try {
            user = userDAO.getUserByID(id);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e) {
            };
        }
        return user;
    }

    @Override
    public void deleteUser(long userID) throws UserServiceException {
        try {
            userDAO.deleteUserFromDB(userID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public void changePassword(long userID, String oldPassword, String newPassword, String repeatPassword) throws UserServiceException {
        if (LoginPasswordValidator.validationPassword(oldPassword) && LoginPasswordValidator.validationPassword(newPassword)
                && LoginPasswordValidator.validationPassword(repeatPassword)) {
            if (newPassword.equals(repeatPassword)) {
                User user = getUserById(userID);
                if (oldPassword.equals(user.getPassword())) {
                    try {
                        userDAO.changeUserPassword(userID, newPassword);
                    } catch (DAOException e) {
                        LOGGER.warn(e);
                        throw new UserServiceException(e);
                    }
                }
            } else {
                throw new UserServiceException("Passwords do not much");
            }
        } else {
            throw new UserServiceException("Incorrect password");
        }
    }

    @Override
    public void blockUser(long userID) throws UserServiceException {
        try {
            userDAO.blockUser(userID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public void unblockUser(long userID) throws UserServiceException {
        try {
            userDAO.unblockUser(userID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    private User buildTempUser(String login, String password, String firstName, String lastName, String passportSerialNumber,
                               String driverLicenceNumber, String email, String phoneNumber) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassportSerialNumber(passportSerialNumber);
        user.setDriverLicenceNumber(driverLicenceNumber);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
