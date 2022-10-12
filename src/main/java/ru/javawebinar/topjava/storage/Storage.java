package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage implements MealStorage {
    private static final ConcurrentMap<Integer, Meal> mealConcurrentMap = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal add(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(nextId.incrementAndGet());
            mealConcurrentMap.putIfAbsent(meal.getId(), meal);
            return meal;
        }
        return mealConcurrentMap.compute(meal.getId(), (id, idMeal) -> meal);
    }

    @Override
    public void delete(int id) {
        mealConcurrentMap.remove(id);
    }

    @Override
    public Meal get(int id) {
        return mealConcurrentMap.get(id);
    }


    @Override
    public Collection<Meal> getAll() {
        return mealConcurrentMap.values();
    }
}
