package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    List<MealTo> getAllByUserId(int userId, int calories) throws NotFoundException;

    void update(Meal meal, int userId);

    Meal get(int id, int userId);
}