package org.example.dao;

import org.example.beans.AdminUserInfo;
import org.example.beans.UserInfo;
import org.example.exception.DAOException;
import org.example.model.User;

import java.util.List;

/**
 * Interface handling user-related database operations.
 */
public interface UserDao {

    List<AdminUserInfo> GetAllUsersInfo() throws DAOException;
    /**
     * Validates user sign-in credentials.
     * @param login The user's login username.
     * @param password The user's password.
     * @return User object if the login is successful.
     * @throws DAOException if there's an error during sign-in.
     */
    User signIn(String login, String password) throws DAOException;

    /**
     * Registers a new user.
     * @param user The user object containing registration details.
     * @return ID of the registered user.
     * @throws DAOException if there's an error during registration.
     */
    UserInfo registration(User user) throws DAOException;

}
