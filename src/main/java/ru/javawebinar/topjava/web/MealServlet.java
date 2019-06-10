package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final List<MealTo> list = MealsUtil.getFilteredWithExcess(MealsUtil.getMeals(), LocalTime.MIN,
            LocalTime.MAX,2000);
    private static final Map<Long, MealTo> mapMealTo = new ConcurrentHashMap<>();
    static {
        list.forEach(l -> mapMealTo.put(l.getId(), l));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        request.setAttribute("mapTo", mapMealTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getPost = request.getParameter("delete");
        long id = Long.parseLong(getPost);
        mapMealTo.remove(id);

        request.setAttribute("mapTo", mapMealTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
