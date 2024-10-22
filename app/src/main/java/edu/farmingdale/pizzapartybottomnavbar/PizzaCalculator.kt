package edu.farmingdale.pizzapartybottomnavbar
import edu.farmingdale.pizzapartybottomnavbar.HungerLevel
import kotlin.math.ceil


const val SLICES_PER_PIZZA = 8

enum class HungerLevel(val numSlices: Int) {
    LIGHT(2),
    MEDIUM(3),
    HUNGRY(4),
    RAVENOUS(5)
}


class PizzaCalculator(
    private val partySize: Int,
    private val hungerLevel: HungerLevel
) {
    val totalPizzas: Int
        get() = ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
}
