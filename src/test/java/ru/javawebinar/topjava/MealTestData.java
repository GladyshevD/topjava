package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(2019, Month.MAY, 30, 10, 0),
            "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2019, Month.MAY, 30, 13, 0),
            "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2019, Month.MAY, 30, 20, 0),
            "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2019, Month.MAY, 31, 10, 0),
            "Завтрак", 1000);
    public static final Meal MEAL5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2019, Month.MAY, 31, 13, 0),
            "Обед", 500);
    public static final Meal MEAL6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2019, Month.MAY, 31, 20, 0),
            "Ужин", 510);

    public static List<Meal> meals = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static List<Meal> mealAdd(Meal meal) {
        List<Meal> mealsDuplicate = new LinkedList<>(meals);
        mealsDuplicate.add(meal);
        return mealsDuplicate;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
