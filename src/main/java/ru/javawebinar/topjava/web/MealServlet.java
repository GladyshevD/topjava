package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private volatile List<Meal> listOfMeals = new ArrayList<>(MealsUtil.getMeals());
    private List<MealTo> listOfMealsTo = MealsUtil.getFilteredWithExcess(listOfMeals, LocalTime.MIN, LocalTime.MAX, 2000);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("delete") != null) {
            long id = Long.parseLong(request.getParameter("delete"));
            for (int i = 0; i < listOfMeals.size(); i++) {     //couldn't use listOfMeals.foreach because of Concurrent Exception
                if (listOfMeals.get(i).getId() == id) listOfMeals.remove(i);   //only traditional for loop
            }
        } else if (request.getParameter("edit") != null) {
            long id = Long.parseLong(request.getParameter("edit"));
            Meal mealForEdit = null;
            for (int i = 0; i < listOfMeals.size(); i++) {     //couldn't use listOfMeals.foreach because of Concurrent Exception
                if (listOfMeals.get(i).getId() == id) {        //only traditional for loop
                    mealForEdit = listOfMeals.get(i);
                    listOfMeals.remove(i);
                }
            }
            request.setAttribute("mealForEdit", mealForEdit);
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        }

        listOfMealsTo = MealsUtil.getFilteredWithExcess(listOfMeals, LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("list", listOfMealsTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        String dateTimeForParse = request.getParameter("datetime");
        LocalDateTime parseDate = LocalDateTime.parse(dateTimeForParse);
        String parseDescription = request.getParameter("description");
        int parseCalories = (int) Long.parseLong(request.getParameter("calories"));
        listOfMeals.add(new Meal(parseDate, parseDescription, parseCalories));
        listOfMeals.sort(Comparator.comparing(Meal::getDateTime));
        listOfMealsTo = MealsUtil.getFilteredWithExcess(listOfMeals, LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("list", listOfMealsTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
