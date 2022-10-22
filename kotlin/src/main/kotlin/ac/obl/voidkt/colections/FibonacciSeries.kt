package ac.obl.voidkt.colections

fun main() {
    val numbers = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }
    println(numbers.take(10).toList())
}