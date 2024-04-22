package com.kapilyarnyPodsos.zapisyakal.food

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import kotlin.math.absoluteValue

class Eating(private var id: Int = 0) {
    private var meals = mutableListOf<Pair<Meal,Int>>()
    companion object {
        var idCount = 1
    }
    init {
        id = idCount
        idCount++
    }
    fun getId(): Int {
        return id
    }
    fun getMeals(): List<Pair<Meal,Int>> {
        return meals.toList()
    }
    fun addMeal(meal: Meal, weight: Int) {
        meals.add(Pair(meal,weight))
    }

    override fun toString(): String {
        return "$id прием пищи"
    }

}