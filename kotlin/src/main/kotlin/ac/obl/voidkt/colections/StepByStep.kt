package ac.obl.voidkt.colections

/*
 * We have an array where each element is the number of steps taken until that moment
 * (given by the position in the array).
 * How to get the maximum number of steps in a minute with one line of code?
 */

val steps = listOf(
    0,
    71,
    140,
    212,
    283,
    358,
    430,
    501,
    575,
    640,
    708,
    780
)

fun main() {
    val maxStepsPerMinute = steps.zipWithNext{a, b -> b - a}.maxOrNull()
    println(maxStepsPerMinute)
}