package com.kapilyarnyPodsos.zapisyakal.workingWithResource

import android.content.Context
import com.kapilyarnyPodsos.zapisyakal.food.Meal
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.TreeMap

class WorkingWithMeals(private val context: Context) {
    companion object{
        var mealsInList = mutableListOf<Meal>()
        var mealsInMap = TreeMap<String, Meal>()
    }
    private var fileName = "Meals.json"
    fun fill() {
        val jsonString = context.openFileInput(fileName).bufferedReader().use {
            it.readText()
        }
        mealsInList = Json.decodeFromString<List<Meal>>(jsonString).toMutableList()
        mealsInList.forEach {
            mealsInMap[it.toString()]=it
        }
    }

    fun addMeal(meal: Meal) {
        mealsInList.add(meal)
        mealsInMap[meal.toString()]=meal
        try {
            val jsonString = Json.encodeToString(ListSerializer(Meal.serializer()), mealsInList)
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                val writer = BufferedWriter(OutputStreamWriter(it))
                writer.write(jsonString)
                writer.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}