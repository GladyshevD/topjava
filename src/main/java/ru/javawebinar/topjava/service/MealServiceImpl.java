package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal create(Meal meal, int userId) throws NotFoundException {
        return checkNotFound(mealRepository.save(meal, userId), meal.toString());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(mealRepository.delete(id, userId), id);
    }

    @Override
    public List<MealTo> getAllByUserId(int userId, int calories) throws NotFoundException {
        return MealsUtil.getWithExcess(mealRepository.getByUserId(userId), calories);
    }

    @Override
    public void update(Meal meal, int userId) {
        checkNotFound(mealRepository.save(meal, userId), meal.toString());
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(mealRepository.get(id, userId), id);
    }
}