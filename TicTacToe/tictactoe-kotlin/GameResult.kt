sealed class GameResult {
    data class Winner(val player: Player): GameResult()
    object Ongoing: GameResult()
    object Tie: GameResult()
}