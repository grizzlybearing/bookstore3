package org.example.service;

import org.example.beans.AdminUserInfo;
import org.example.beans.UserInfo;
import org.example.exception.ServiceException;
import org.example.model.User;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    UserInfo registration(User user) throws ServiceException;

    List<AdminUserInfo> getAllUsersInfo() throws  ServiceException;

}
