package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void addMeal(Meal meal);

    Meal getMealById(Integer id);

    void deleteMeal(Integer id);

    void updateMeal(Meal meal);

    List<Meal> getAllMeal();
}
