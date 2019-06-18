package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;
    private UserRepository userRepository;
    private ProfileRestController profile;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, UserRepository userRepository, ProfileRestController profile) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.profile = profile;
    }

    @Override
    public Meal create(Meal meal) throws NotFoundException {
        return checkNotFound(mealRepository.save(meal), meal.toString());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(checkId(id) ? mealRepository.delete(id) : null, id);
    }

    @Override
    public List<MealTo> getAllByUserId() throws NotFoundException {
        int userId = profile.get().getId();
        return MealsUtil.getFilteredWithExcess(mealRepository.getForLoggedUser(userId),
                userRepository.get(userId).getCaloriesPerDay(), LocalTime.MIN, LocalTime.MAX);
    }

    @Override
    public void update(Meal meal) {
        checkNotFound(checkId(meal.getId()) ? mealRepository.save(meal) : null, meal.toString());
    }

    @Override
    public Meal get(int id) {
        return checkNotFoundWithId(checkId(id) ? mealRepository.get(id) : null, id);
    }

    private boolean checkId(Integer id) {
        return profile.get().getId().equals(mealRepository.get(id).getId());
    }
}