package com.kapilyarnyPodsos.zapisyakal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.kapilyarnyPodsos.zapisyakal.food.Eating
import kotlin.math.absoluteValue

class AddMeal : ComponentActivity() {

    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_meal)
        fill()
        editText = this.findViewById(R.id.Weight)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()) {
                    val textViewProtein: TextView = findViewById(R.id.ProteinsValue)
                    textViewProtein.text = "0"
                    val textViewFats: TextView = findViewById(R.id.FatsValue)
                    textViewFats.text = "0"
                    val textViewCarbons: TextView = findViewById(R.id.CarbonsValue)
                    textViewCarbons.text = "0"
                } else {

                    val textViewProtein: TextView = findViewById(R.id.ProteinsValue)
                    val proteinCount100 = SearchMeal.meal?.getProteins()
                    val proteinCount1: Double? = proteinCount100?.toDouble()?.div(100)
                    if (proteinCount1 != null) {
                        val formattedInput = String.format("%.2f", (proteinCount1 * s.toString().toDouble()))
                        textViewProtein.text = formattedInput
                    }
                    val textViewFats: TextView = findViewById(R.id.FatsValue)
                    val fatsCount100 = SearchMeal.meal?.getFats()
                    val fatsCount1: Double? = fatsCount100?.toDouble()?.div(100)
                    if (fatsCount1 != null) {
                        val formattedInput = String.format("%.2f", (fatsCount1 * s.toString().toDouble()))
                        textViewFats.text = formattedInput
                    }
                    val textViewCarbons: TextView = findViewById(R.id.CarbonsValue)
                    val carbonsCount100 = SearchMeal.meal?.getCarbons()
                    val carbonsCount1: Double? = carbonsCount100?.toDouble()?.div(100)
                    if (carbonsCount1 != null) {
                        val formattedInput = String.format("%.2f", (carbonsCount1 * s.toString().toDouble()))
                        textViewCarbons.text = formattedInput
                    }
                }
            }
        })
    }

    private fun fill() {
        val textViewName: TextView = findViewById(R.id.Name)
        textViewName.text = SearchMeal.meal.toString()
        val textViewProtein: TextView = findViewById(R.id.ProteinsValue)
        textViewProtein.text = SearchMeal.meal?.getProteins().toString()
        val textViewFats: TextView = findViewById(R.id.FatsValue)
        textViewFats.text = SearchMeal.meal?.getFats().toString()
        val textViewCarbons: TextView = findViewById(R.id.CarbonsValue)
        textViewCarbons.text = SearchMeal.meal?.getCarbons().toString()
    }

    fun toFood(v: View) {
        editText = this.findViewById(R.id.Weight)
        val eating = Eating()
        SearchMeal.meal?.let { eating.addMeal(it,editText.text.toString().toInt()) }
        Food.mealsInList.add(eating)
        val intent = Intent (this, Food::class.java)
        startActivity(intent)
    }
}
