package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealService {
    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void removeMeal(Meal meal);

    void removeMealById(int id);

    Meal getMealById(int id);

    Collection<Meal> getAll();
}
