package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.MealStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private MealStore mealStore = new MealStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("delete") != null) {
            long id = Long.parseLong(request.getParameter("delete"));
            mealStore.remove(id);
            response.sendRedirect("/topjava/meals");
        } else if (request.getParameter("edit") != null) {
            long id = Long.parseLong(request.getParameter("edit"));
            request.setAttribute("mealForEdit", mealStore.getMealById(id));
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        } else {
            request.setAttribute("list", mealStore.getMealsTo());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        if (!request.getParameter("id").equals("")) {
            long id = Long.parseLong(request.getParameter("id"));
            mealStore.remove(id);
        }
        String dateTimeForParse = request.getParameter("datetime");
        LocalDateTime parseDate = LocalDateTime.parse(dateTimeForParse);
        String parseDescription = request.getParameter("description");
        int parseCalories = (int) Long.parseLong(request.getParameter("calories"));
        mealStore.addMeal(parseDate, parseDescription, parseCalories);

        request.setAttribute("list", mealStore.getMealsTo());
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
