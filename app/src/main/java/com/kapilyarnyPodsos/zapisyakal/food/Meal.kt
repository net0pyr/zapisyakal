package com.kapilyarnyPodsos.zapisyakal.food

import kotlinx.serialization.Serializable

@Serializable
data class Meal(private var name: String, private var proteins: Int, private var carbons: Int, private var fats: Int) {
    private var kalls = proteins*4 + carbons*4 + fats * 9
    override fun toString(): String {
        return name
    }
    fun getProteins():Int {
        return proteins
    }
    fun getCarbons():Int {
        return carbons
    }
    fun getFats():Int {
        return fats
    }
    fun getKalls():Int {
        return kalls
    }
}