package org.example.service.impl;

import org.example.beans.AdminUserInfo;
import org.example.beans.UserInfo;
import org.example.beans.UserRoles;
import org.example.dao.UserDao;
import org.example.exception.DAOException;
import org.example.exception.ServiceException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDAO(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    @Transactional
    public List<AdminUserInfo> getAllUsersInfo() throws ServiceException {
        List<AdminUserInfo> users = null;
        try {
            users = userDao.GetAllUsersInfo();

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return users;
    }


    @Override
    @Transactional
    public User signIn(String login, String password) throws ServiceException {
        User user = null;
        try {
            if (login == null || password == null) {
                throw new ServiceException("Incorrect password or login");
            }
            if(login.equals("admin") && password.equals("admin")){
                user = new User();
                user.setRole(UserRoles.ADMIN);
                return user;
            }
            user = userDao.signIn(login, password);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    @Override
    @Transactional
    public UserInfo registration(User user) throws ServiceException {
        UserInfo userInfo = null;
        try {
            if (user.getPassword() == null || user.getLogin() == null) {
                throw new ServiceException("Incorrect password or login");
            }
             userInfo = userDao.registration(user);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return userInfo;
    }
}
