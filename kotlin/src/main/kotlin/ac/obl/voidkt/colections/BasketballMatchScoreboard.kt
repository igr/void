package ac.obl.voidkt.colections

/*
 * Two teams play a basketball match. Given the list of <player, team, points> for each player
 * in the match, we have to get the match scoreboard with only one line of code.
 */

enum class Team {
    YELLOW, BLUE
}

data class PlayerGame (
    val player : String,
    val team : Team,
    val points : Int
)

val match = listOf(
    PlayerGame("13", Team.YELLOW, 12),
    PlayerGame("7", Team.YELLOW, 7),
    PlayerGame("3", Team.YELLOW, 20),
    PlayerGame("22", Team.YELLOW, 16),
    PlayerGame("33", Team.YELLOW, 4),
    PlayerGame("45", Team.YELLOW, 26),
    PlayerGame("8", Team.BLUE, 17),
    PlayerGame("3", Team.BLUE, 15),
    PlayerGame("21", Team.BLUE, 16),
    PlayerGame("30", Team.BLUE, 12),
    PlayerGame("45", Team.BLUE, 24)
)

fun main() {
    val sums = match.groupingBy{it.team}.fold(0) {sum, element -> sum + element.points}
    println(sums)
}