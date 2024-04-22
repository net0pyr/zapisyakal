package com.kapilyarnyPodsos.zapisyakal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.ComponentActivity
import android.view.inputmethod.InputMethodManager
import com.kapilyarnyPodsos.zapisyakal.food.Meal
import com.kapilyarnyPodsos.zapisyakal.workingWithResource.WorkingWithMeals

class SearchMeal : ComponentActivity() {

    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    companion object {
        var meal: Meal? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serch_meal)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        searchView = findViewById(R.id.searchView)
        listView = findViewById(R.id.listView)

        val items = mutableListOf<String>()

        WorkingWithMeals.mealsInList.forEach() {
            items.add(it.toString())
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(searchView.findFocus(), InputMethodManager.SHOW_IMPLICIT)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            meal = WorkingWithMeals.mealsInMap[selectedItem]
            val intent = Intent (this, AddMeal::class.java)
            startActivity(intent)
            finish()
        }
    }
}