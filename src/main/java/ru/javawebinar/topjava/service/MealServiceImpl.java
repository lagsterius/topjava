package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public class MealServiceImpl implements MealService {
    private MealRepository mealDao = new InMemoryMealRepository();

    @Override
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealDao.updateMeal(meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        mealDao.removeMeal(meal);
    }

    @Override
    public void removeMealById(int id) {
        mealDao.removeMealById(id);
    }

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealDao.getAll();
    }
}
