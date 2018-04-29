package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealService mealService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealService = new MealServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = req.getParameter("action");
        if (action != null && !action.isEmpty()) {
            int id = Integer.parseInt(req.getParameter("id"));

            if (action.equalsIgnoreCase("edit")) {
                req.setAttribute("editedId", id);
            } else if (action.equalsIgnoreCase("remove")) {
                mealService.removeMealById(id);
            }
        }
        req.setAttribute("mealList", MealsUtil.getWithExceeded(mealService.getAll(),2000));
        req.getRequestDispatcher("/pages/meals.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Integer id;
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.valueOf(req.getParameter("calories"));
        try {
            id = Integer.valueOf(req.getParameter("id"));
            mealService.updateMeal(new Meal(id, localDateTime, description, calories));
        } catch (NumberFormatException e) {
            mealService.addMeal(new Meal(localDateTime, description, calories));
        } finally {
            resp.sendRedirect("./meals");
        }
    }
}