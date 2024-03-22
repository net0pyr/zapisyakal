package com.kapilyarnyPodsos.zapisyakal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity


class Settings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun navigationSport(v: View) {
        val intent = Intent (this, Sport::class.java)
        startActivity(intent)
    }
    fun navigationFood(v: View) {
        val intent = Intent (this, Food::class.java)
        startActivity(intent)
    }
}
