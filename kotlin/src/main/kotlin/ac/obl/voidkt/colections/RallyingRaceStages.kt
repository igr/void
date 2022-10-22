package ac.obl.voidkt.colections

/*
 * A rallying race has 4 stages. At the end of each stage, a measure is taken.
 * This measure has time accumulated in seconds and distance accumulated in meters.
 * Besides this measure, we have the initial measure at the starting point with (0,0).
 * How to get the fastest stage with only one line of code?
 */

data class Measure (
    val number : Int,
    val secondsAcc : Int,
    val metersAcc : Int
)

val measures = mutableListOf (
    Measure(0, 0, 0),
    Measure(1, 302, 8090),
    Measure(2, 689, 17655),
    Measure(3, 1204, 32655),
    Measure(4, 1425, 39884)
)

fun main() {
    val fastestStageNumber = measures.zipWithNext{a, b -> (b.metersAcc - a.metersAcc)/(b.secondsAcc - a.secondsAcc)}.mapIndexed{idx, value -> Pair(idx+1, value)}.maxByOrNull{it.second}
    println("The fastest stage is stage number ${fastestStageNumber?.first} with speed = ${fastestStageNumber?.second} m/s")
}