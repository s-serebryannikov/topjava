package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredTos;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;

@Controller
public class MealRestController {
    private final MealService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id, int userId) {
        log.info("get meal{}", id);
        return service.get(id, userId);
    }

    public Meal save(Meal meal, int userId) {
        log.info("create meal{}", meal);
        checkNew(meal);
        return service.save(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete meal{}", id);
        service.delete(id, userId);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll meals");
        return service.getAll(SecurityUtil.authUserId());
    }

    public List<MealTo> getFilterMealsToByDateTime(LocalDate dateStart, LocalDate dateEnd,
                                                   LocalTime timeStart, LocalTime timeEnd, int userId) {
        log.info("getFilterMealsToByDateTime");
        List<Meal> mealsFilteredByDate = service.getMealFilteredByDate(dateStart, dateEnd, userId);
        return getFilteredTos(mealsFilteredByDate, authUserCaloriesPerDay(), timeStart, timeEnd);
    }
}
