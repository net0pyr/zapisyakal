package com.kapilyarnyPodsos.zapisyakal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity


class Sport : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport)
    }

    fun navigationFood(v: View) {
        val intent = Intent (this, Food::class.java)
        startActivity(intent)
    }
    fun navigationSettings(v: View) {
        val intent = Intent (this, Settings::class.java)
        startActivity(intent)
    }
}
