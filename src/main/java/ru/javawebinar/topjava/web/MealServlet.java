package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealStorage mealStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("init ServletConfig");
        super.init(config);
        mealStorage = new Storage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("method doGet");
        String action = request.getParameter("action");
        Meal meal;
        switch (action == null ? "viewAll" : action) {
            case "delete":
                log.debug("action delete");
                int id = getId(request);
                mealStorage.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
                log.debug("action create");
                request.setAttribute("meal", mealStorage.createOrUpdate(new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0)));
                request.getRequestDispatcher("/updateMeal.jsp").forward(request, response);
            case "update":
                log.debug("action update");
                meal = mealStorage.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/updateMeal.jsp").forward(request, response);
                break;
            case "viewAll":
            default:
                log.debug("action default");
                request.setAttribute("meals",
                        MealsUtil.filteredByStreams(mealStorage.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("method doPost");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(Integer.valueOf(id), dateTime, description, calories);
        mealStorage.createOrUpdate(meal);
        response.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
