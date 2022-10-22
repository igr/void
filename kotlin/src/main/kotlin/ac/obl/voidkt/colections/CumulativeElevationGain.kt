package ac.obl.voidkt.colections

/*
 * Cumulative Elevation Gain or Total Ascent is:
 * the sum of every gain in elevation throughout an entire trip
 * We have an array with elevations in meters along the route. To simplify,
 * suppose we do a relatively short cycling stage (2.5 km). Every 100 meters
 * traveled we have one elevation measure, so we have 25 measures.
 * How to obtain the cumulative gain elevation in meters with only one line of code?
 */

val altitudes = listOf(
    800, 805, 804, 800, 803, 806, 809, 805, 800, 798, 796, 799,
    803, 805, 808, 812, 815, 816, 819, 822, 826, 830, 832, 835, 837
)

fun main() {
    val cumulativeGain = altitudes.zipWithNext{a,b -> b - a}.filter{it > 0}.sum()
    println(cumulativeGain)
}