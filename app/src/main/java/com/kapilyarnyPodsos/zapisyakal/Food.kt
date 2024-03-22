package com.kapilyarnyPodsos.zapisyakal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStreamReader
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
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        readFromFileNorma()
        readFromFileToday()
        val textView: TextView = findViewById(R.id.kall)
        textView.text = "$nowKall/$kall"
    }

    fun navigationSport(v: View) {
        val intent = Intent (this, Sport::class.java)
        startActivity(intent)
    }
    fun navigationSettings(v: View) {
        val intent = Intent (this, Settings::class.java)
        startActivity(intent)
    }

    fun readFromFileNorma() {

        var inputStreamReader = InputStreamReader(FileInputStream(System.getenv("/home/net0pyr/zapisyakal/app/norma")))
        var reader = BufferedReader(inputStreamReader)
        var fileArray = mutableListOf<String>()
        var line: String?
        while(reader.readLine().also { line = it } != null) {
            fileArray.add(line!!)
        }

        carbo = fileArray[0].toInt()
        protein = fileArray[1].toInt()
        fats = fileArray[2].toInt()

        kall = 4*carbo+4*protein+9*fats

        reader.close()

    }

    fun readFromFileToday() {
        var inputStreamReader = InputStreamReader(FileInputStream(System.getenv("/home/net0pyr/zapisyakal/app/todayFood")))
        var reader = BufferedReader(inputStreamReader)
        var fileArray = mutableListOf<String>()
        var line: String?
        while(reader.readLine().also { line = it } != null) {
            fileArray.add(line!!)
        }

        nowCarbo = fileArray[0].toInt()
        nowProtein = fileArray[1].toInt()
        nowFats = fileArray[2].toInt()

        nowKall = 4*carbo+4*protein+9*fats

        reader.close()
    }
}
