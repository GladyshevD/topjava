package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface Storable {
    LocalTime min = LocalTime.MIN;
    LocalTime max = LocalTime.MAX;

    void addMeal(LocalDateTime ldt, String description, int calories);

    void remove(long id);

    Meal getMealById(long id);

    List<MealTo> getMealsTo();
}
