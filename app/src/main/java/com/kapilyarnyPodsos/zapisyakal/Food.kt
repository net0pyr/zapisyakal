package com.kapilyarnyPodsos.zapisyakal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStreamReader
import kotlin.properties.Delegates


class Food : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
    }

    fun navigationSport(v: View) {
        val intent = Intent (this, Sport::class.java)
        startActivity(intent)
    }
    fun navigationSettings(v: View) {
        val intent = Intent (this, Settings::class.java)
        startActivity(intent)
    }
    fun progressBar(v: View) {
        //val progressBar: ProgressBar = findViewById(R.id.progressBar)
        //progressBar.progress = 50
    }
    val inputStreamReader = InputStreamReader(FileInputStream(System.getenv("norma")))
    val reader = BufferedReader(inputStreamReader)
    var kal by Delegates.notNull<Int>()
    var protein by Delegates.notNull<Int>()
    var carbo by Delegates.notNull<Int>()
    var fats by Delegates.notNull<Int>()

    

}
