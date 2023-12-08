package org.example.service;

import org.example.beans.DishInfo;
import org.example.exception.ServiceException;
import org.example.model.Dish;

import java.util.List;

public interface DishService {
    public List<DishInfo> getUserDishesInBasket(int userId) throws ServiceException;

    public List<Dish> getDishesByCategoryId(int id) throws ServiceException;

    public void decrementDishAmount(int userId, int dishId) throws ServiceException;

    public void incrementDishAmount(int userId, int dishId) throws ServiceException;

    public void addDishToBasket(int userId, int dishId) throws ServiceException;

}
