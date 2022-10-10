package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapStorage implements Storage {

    ConcurrentMap<Integer, Meal> mealConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void addMeal(Meal meal) {
        mealConcurrentMap.putIfAbsent(meal.getId(), meal);
    }

    @Override
    public Meal getMealById(Integer id) {
        return mealConcurrentMap.get(id);
    }

    @Override
    public void deleteMeal(Integer id) {
        mealConcurrentMap.remove(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealConcurrentMap.putIfAbsent(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAllMeal() {
        return new ArrayList<>(mealConcurrentMap.values());
    }
}
