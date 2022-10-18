package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public class MealRestController {
    private MealService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public Meal get(int id, int authUserId) {
        log.info("get meal{}", id);
        return service.get(id, authUserId);
    }

    public Meal save(Meal meal, int authUserId) {
        log.info("create meal{}", meal);
        checkNew(meal);
        return service.save(meal, authUserId);
    }

    public void delete(int id, int authUserId) {
        log.info("delete meal{}", id);
        service.delete(id, authUserId);
    }

    public Collection<Meal> getAll() {
        log.info("getAll meals");
        return service.getAll(SecurityUtil.authUserId());
    }
}
