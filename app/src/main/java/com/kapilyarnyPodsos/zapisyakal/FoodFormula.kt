package com.kapilyarnyPodsos.zapisyakal

import kotlinx.serialization.Serializable

@Serializable
class FoodFormula(private var name: String, private var proteins: Int, private var carbons: Int, private var fats: Int) {

}