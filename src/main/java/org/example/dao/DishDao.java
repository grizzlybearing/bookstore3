package org.example.dao;

import org.example.beans.DishInfo;
import org.example.exception.DAOException;
import org.example.model.Dish;

import java.util.List;

/**
 * Interface handling dish-related database operations.
 */
public interface DishDao {

    List<Dish> getDishesListByCategoryId(int categoryId) throws DAOException;

    boolean decrementDishAmountInBasket(int userId, int dishId) throws DAOException;
    boolean incrementDishAmountInBasket(int userId, int dishId) throws DAOException;

    boolean addDishToBasket(int userId, int dishId) throws DAOException;

    Dish getDishById(int id) throws DAOException;

    List<DishInfo> getDishesByUserId(int id) throws DAOException;

    boolean makeOrder(int userId) throws DAOException;
}
