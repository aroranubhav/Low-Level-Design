class Board constructor(
    private val size: Int
) {

    private lateinit var board: MutableList<MutableList<String>>
    private lateinit var rowCounts: MutableMap<Int, MutableMap<String, Int>>()
    private lateinit var colCounts: MutableMap<Int, MutableMap<String, Int>>()
    private lateinit var diagCounts: MutableMap<String, MutableMap<String, Int>>()
    private val boardSize: Int = size
    private var currCounter: Int = 0

    fun reset(size: Int) {
        board = MutableList(size) {MutableList(size) {""}}
        rowCounts = mutableMapOf<Int, MutableMap<String, Int>>()
        colCounts = mutableMapOf<Int, MutableMap<String, Int>>()
        diagCounts = mutableMapOf<String, MutableMap<String, Int>>()
        currCounter = 0
    }

    fun place(player: Player, x: Int, y: Int): GameResult {
        val identifier = player.identifier

        if (!(x >= 0 && x < boardSize)||!(y >= 0 && y < boardSize)) {
            throw IllegalArgumentException("Invalid move: out of bounds")
        }

        if (board[x][y] != "") {
            throw IllegalArgumentException("Invalid move: cell already occupied")
        }

        board[x][y] = identifier
        currCounter += 1

        //row
        val rowInner = rowCounts.getOrPut(x) { mutableMapOf() }
        rowInner[identifier] = (rowInner[identifier] ?: 0) + 1

        if (rowCounts[x]!![identifier]!! == boardSize) {
            return GameResult.Winner(player)
        }

        //column
        val colInner = colCounts.getOrPut((y)) { mutableMapOf() }
        colInner[identifier] = (colInner[identifier] ?: 0) + 1
        
        if (colCounts[y]!![identifier]!! == boardSize) {
            return GameResult.Winner(player)
        }

        //diagonal

        //forwards
        if (x == y) {
            val forwardDiagInner = diagCounts.getOrPut("forwards") { mutableMapOf() }
            forwardDiagInner[identifier] = (forwardDiagInner[identifier] ?: 0) + 1

            if (diagCounts["forwards"]!![identifier]!! == boardSize) {
                return GameResult.Winner(player)
            }
        }

        //backwards

        if (x + y == boardSize - 1) {
            val backwardsDiagInner = diagCounts.getOrPut("backwards") { mutableMapOf() }
            backwardsDiagInner[identifier] = (backwardsDiagInner[identifier] ?: 0) + 1

            if (diagCounts["backwards"]!![identifier]!! == boardSize) {
                return GameResult.Winner(player)
            }
        }

        //tie check

        if (currCounter == boardSize * boardSize) {
            println("Game Tied")
            return GameResult.Tie
        }
        return GameResult.Ongoing
    }
}



