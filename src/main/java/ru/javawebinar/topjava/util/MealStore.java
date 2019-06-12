package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealStore implements Storable {
    private AtomicLong idCount = new AtomicLong(1L);
    private Map<Long, Meal> mealMap = new ConcurrentHashMap<>();

    @Override
    public void addMeal(LocalDateTime ldt, String description, int calories) {
        Meal newMeal = new Meal(idCount.getAndIncrement(), ldt, description, calories);
        mealMap.put(newMeal.getId(), newMeal);
    }

    @Override
    public void remove(long id) {
        mealMap.remove(id);
    }

    @Override
    public Meal getMealById(long id) {
        return mealMap.get(id);
    }

    @Override
    public List<MealTo> getMealsTo() {
        List<Meal> listMeal = new ArrayList<>(mealMap.values());
        listMeal.sort(Comparator.comparing(Meal::getDateTime));
        return MealsUtil.getFilteredWithExcess(listMeal, min, max, 2000);
    }
}
