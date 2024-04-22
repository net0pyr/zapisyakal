package com.kapilyarnyPodsos.zapisyakal

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.kapilyarnyPodsos.zapisyakal.food.Eating
import com.kapilyarnyPodsos.zapisyakal.food.Meal
import com.kapilyarnyPodsos.zapisyakal.workingWithResource.WorkingWithMeals
import kotlin.properties.Delegates


class Food : ComponentActivity() {
    var kall by Delegates.notNull<Int>()
    var protein by Delegates.notNull<Int>()
    var carbo by Delegates.notNull<Int>()
    var fats by Delegates.notNull<Int>()

    var nowKall by Delegates.notNull<Int>()
    var nowProtein by Delegates.notNull<Int>()
    var nowCarbo by Delegates.notNull<Int>()
    var nowFats by Delegates.notNull<Int>()

    companion object {
        var mealsInList = mutableListOf<Eating>()
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val workingWithMeals = WorkingWithMeals(applicationContext)
        val meal = Meal("Marshmello", 2, 80,0)
        workingWithMeals.addMeal(meal)
        val meal1 = Meal("Chiken", 21, 10,4)
        workingWithMeals.addMeal(meal1)
        val meal2 = Meal("Fish", 21, 10,4)
        workingWithMeals.addMeal(meal2)
        val meal3 = Meal("Meat", 21, 10,4)
        workingWithMeals.addMeal(meal3)
        val meal4 = Meal("Rice", 21, 10,4)
        workingWithMeals.addMeal(meal4)
        workingWithMeals.fill()
        readFromFileNorma()
        readFromFileToday()
        fillProgressBar()


        val listView:ListView = findViewById(R.id.listView)

        val items = mutableListOf<String>()

        mealsInList.forEach() {
            items.add(it.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter


        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            SearchMeal.meal = WorkingWithMeals.mealsInMap[selectedItem]
            val intent = Intent (this, AddMeal::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun navigationSport(v: View) {
        val intent = Intent (this, Sport::class.java)
        startActivity(intent)
    }
    fun navigationSettings(v: View) {
        val intent = Intent (this, Settings::class.java)
        startActivity(intent)
    }

    fun addNewMeal(v:View) {
        val intent = Intent (this, SearchMeal::class.java)
        startActivity(intent)
    }

    private fun readFromFileNorma() {
        val lines: List<String> = applicationContext.assets
            .open("norma.txt")
            .bufferedReader()
            .use {
                it.readLines()
            }

        carbo = lines[0].toInt()
        protein = lines[1].toInt()
        fats = lines[2].toInt()

        kall = 4*carbo+4*protein+9*fats
    }

    private fun readFromFileToday() {
        val lines: List<String> = applicationContext.assets
            .open("todayFood.txt")
            .bufferedReader()
            .use {
                it.readLines()
            }

        nowCarbo = lines[0].toInt()
        nowProtein = lines[1].toInt()
        nowFats = lines[2].toInt()

        nowKall = 4*nowCarbo+4*nowProtein+9*nowFats
    }

    private fun fillProgressBar() {
        val progressBarKall: ProgressBar = findViewById(R.id.progressBarKall)
        progressBarKall.progress += (nowKall.toDouble()/kall.toDouble()*100).toInt()
        val textViewKall: TextView = findViewById(R.id.kall)
        textViewKall.text = "$nowKall/$kall"

        val progressBarCarbons: ProgressBar = findViewById(R.id.progressBarCarbo)
        progressBarCarbons.progress += (nowCarbo.toDouble()/carbo.toDouble()*100).toInt()
        val textViewCarbons: TextView = findViewById(R.id.carbons)
        textViewCarbons.text = "$nowCarbo/$carbo"

        val progressBarProteins: ProgressBar = findViewById(R.id.progressBarProtein)
        progressBarProteins.progress += (nowProtein.toDouble()/protein.toDouble()*100).toInt()
        val textViewProteins: TextView = findViewById(R.id.proteins)
        textViewProteins.text = "$nowProtein/$protein"

        val progressBarFats: ProgressBar = findViewById(R.id.progressBarFats)
        progressBarFats.progress += (nowFats.toDouble()/fats.toDouble()*100).toInt()
        val textViewFats: TextView = findViewById(R.id.fats)
        textViewFats.text = "$nowFats/$fats"
    }
}
