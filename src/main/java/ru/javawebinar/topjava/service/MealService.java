package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id) throws NotFoundException;

    List<MealTo> getAllByUserId () throws NotFoundException;

    void update(Meal meal);

    Meal get(int id);
}