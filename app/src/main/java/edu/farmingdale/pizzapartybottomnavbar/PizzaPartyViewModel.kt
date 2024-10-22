package edu.farmingdale.pizzapartybottomnavbar

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.ceil

class PizzaPartyViewModel : ViewModel() {
    var totalPizzas by mutableStateOf(0)
        private set
    var numPeopleInput by mutableStateOf("")
    var hungerLevel by mutableStateOf("Medium")

    fun calculateTotalPizzas() {
        val numPeople = numPeopleInput.toIntOrNull() ?: 0
        val slicesPerPizza = 8
        val slicesPerPerson = when (hungerLevel) {
            "Light" -> 2
            "Medium" -> 3
            "Hungry" -> 4
            else -> 5
        }
        totalPizzas = ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
    }
}
