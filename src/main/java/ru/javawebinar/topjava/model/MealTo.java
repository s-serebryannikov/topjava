package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MealTo {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final Integer id;

    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess, Integer id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealTo meal = (MealTo) o;
        return calories == meal.calories && excess == meal.excess && Objects.equals(dateTime, meal.dateTime) && Objects.equals(description, meal.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, description, calories, excess);
    }

}
