package ru.javawebinar.topjava.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.DateUtil;

public class MealController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/create.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private Storage mealStorage;

    public MealController() {
        super();
        mealStorage = new MapStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        Integer mealId = Integer.parseInt(request.getParameter("mealId"));
        switch (action) {
            case "delete":
                mealStorage.deleteMeal(mealId);
                forward = LIST_MEAL;
                request.setAttribute("meals", mealStorage.getAllMeal());
                break;
            case "edit":
                forward = INSERT_OR_EDIT;
                Meal meal = mealStorage.getMealById(mealId);
                request.setAttribute("user", meal);
            case "listMeal":
                forward = LIST_MEAL;
                request.setAttribute("meals", mealStorage.getAllMeal());
            default:
                forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();

        LocalDateTime ldt = DateUtil.formatStringToLDT(request.getParameter("ldt"));
        meal.setDateTime(ldt);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealId = request.getParameter("getId");
        if (mealId == null || mealId.isEmpty()) {
            mealStorage.addMeal(meal);
        } else {
            mealStorage.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("users", mealStorage.getAllMeal());
        view.forward(request, response);
    }
}
