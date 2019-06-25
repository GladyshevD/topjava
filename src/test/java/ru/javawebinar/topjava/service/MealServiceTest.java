package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test (expected = NotFoundException.class)
    public void get() throws NotFoundException {
        Meal meal = mealService.get(MEAL_ID, 1);
        assertMatch(meal, MEAL1);
    }

    @Test (expected = NotFoundException.class)
    public void delete() throws NotFoundException {
        mealService.delete(MEAL_ID, 100010);
        assertMatch(mealDelete(MEAL_ID), mealService.getAll(100010));
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> allBetweenDates = mealService.getBetweenDateTimes(LocalDateTime.of(2019, Month.MAY, 30, 0, 0),
                LocalDateTime.of(2019, Month.MAY, 30, 23, 0), 100000);
        assertMatch(allBetweenDates, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> allBetweenDateTimes = mealService.getBetweenDateTimes(LocalDateTime.of(2019, Month.MAY, 30, 0, 0),
                LocalDateTime.of(2019, Month.MAY, 30, 23, 0), 100000);
        assertMatch(allBetweenDateTimes, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(100000);
        assertMatch(all, meals);
    }

    @Test (expected = NotFoundException.class)
    public void update() throws NotFoundException {
        Meal updated = new Meal(100002, LocalDateTime.of(2019, Month.MAY, 30, 10, 0),
                "Завтрак", 500);
        mealService.update(updated, 1);
        assertMatch(updated, MEAL1);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, Month.MAY, 30, 10, 0),
                "Завтрак", 500);
        meals.add(newMeal);
        mealService.create(newMeal, 100000);
        assertMatch(meals, mealService.getAll(100000));
    }
}