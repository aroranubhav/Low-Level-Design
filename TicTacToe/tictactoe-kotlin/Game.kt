class Game constructor(
    private val p1: Player,
    private val p2: Player,
    private val board: Board
){

    private fun play() {
        var currTurn = 1
        var isGameCompleted = false

        while (!isGameCompleted) {
            val currPlayer = if (currTurn % 2 == 1) p1 else p2
            print("${currPlayer.name}'s turn (${currPlayer.identifier})")

            try {
                print("Enter x: ")
                val x = readLine()?.toIntOrNull() ?: -1  

                print("Enter y: ")
                val y = readLine()?.toIntOrNull() ?: -1

                val result = board.place(currPlayer, x, y)

                when (result) {
                    is GameResult.Winner -> {
                        println("Winner is ${result.player}")
                        isGameCompleted = true
                    }

                    is GameResult.Tie -> {
                        isGameCompleted = true
                    }

                    is GameResult.Ongoing -> {
                        currTurn += 1
                    }
                }
            } catch(e: IllegalArgumentException) {
                print(e)
                continue
            }
        }
    }

    fun main(args: Array<String>) {
        val p1: Player = Player("Al", "X")
        val p2: Player = Player("Maxi", "0")

        val board = Board(3)
        val game = Game(p1, p2, board)
    
        game.play()
    }
}
