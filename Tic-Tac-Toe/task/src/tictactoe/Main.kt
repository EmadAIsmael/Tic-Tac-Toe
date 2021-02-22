package tictactoe


class TicTacToe {

    data class GameStatus(
        var xWins: Boolean = false,
        var oWins: Boolean = false,
        var isDraw: Boolean = false
    )

    private val rows = 3
    private val columns = 3
    private val board = Array(3) { arrayOf("_", "_", "_") }
    private val status = GameStatus()

    fun setX(row: Int, col: Int) {
        board[row - 1][col - 1] = "X"
    }

    fun setO(row: Int, col: Int) {
        board[row - 1][col - 1] = "O"
    }

    private fun isWinner(player: String): Boolean {
        var isWinner: Boolean
        // check columns
        for (r in 1..rows) {
            isWinner = true
            for (c in 1..columns) {
                isWinner = isWinner && (board[r - 1][c - 1] == player)
            }
            if (isWinner) return true
        }
        // check rows
        for (c in 1..columns) {
            isWinner = true
            for (r in 1..rows) {
                isWinner = isWinner && (board[r - 1][c - 1] == player)
            }
            if (isWinner) return true
        }
        // check main diagonal
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player
        ) return true

        // check reverse diagonal
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player
        ) return true

        return false
    }

    private fun drawInitialBoard() {
        val cells = "_".repeat(9)
        for (r in 1..rows)
            for (c in 1..columns)
                board[r - 1][c - 1] = cells[((r - 1) * 3) + c - 1].toString()
        println(this)
    }

    private fun getStatus(cells: String) {
        val numOs = cells.count { it == 'O' }
        val numXs = cells.count { it == 'X' }
        val num_s = cells.count { it == '_' }
        when {
            numOs - numXs >= 2 || numXs - numOs >= 2 -> "Impossible"
            isWinner("O") && isWinner("X") -> "Impossible"
            isWinner("O") && !isWinner("X") -> {
                // "O wins"
                status.oWins = true
            }
            isWinner("X") && !isWinner("O") -> {
                // "X wins"
                status.xWins = true
            }
            num_s == 0 -> {
                // "Draw"
                status.isDraw = true
            }
            num_s > 0 -> "Game not finished"
            else -> ""
        }
    }

    private fun readInintialBoard(): String {
        var initialBoard = "*"
        while (initialBoard.any { it !in "OX_" } ||
            initialBoard.length != 9) {
            print("Enter cells: ")
            initialBoard = readLine()!!
        }
        return initialBoard
    }

    private fun getUserMove(move: String = "X") {
        while (true) {
            try {
                print("Enter the coordinates: ")
                val input = readLine()!!.split(' ')
                if (input.size != 2) throw NumberFormatException("You should enter numbers!")

                val (rn, cn) = arrayOf(input[0].toInt(), input[1].toInt())
                if (rn !in 1..3 || cn !in 1..3)
                    throw CoordinatesOutOfRangeException("Coordinates should be from 1 to 3!")
                if (board[rn - 1][cn - 1] in "OX")
                    throw OccupiedCellChosenException("This cell is occupied! Choose another one!")
                board[rn - 1][cn - 1] = move
                break
            } catch (e: OccupiedCellChosenException) {
                println(e.message)
            } catch (e: CoordinatesOutOfRangeException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println("You should enter numbers!")
            }
        }
        println(this)
    }

    private fun boardAsString(): String {
        var str = ""
        for (r in 1..rows)
            for (c in 1..columns)
                str += board[r - 1][c - 1]
        return str
    }

    fun play() {
        drawInitialBoard()
        val player = arrayOf("X", "O")
        var playerIdx = 0
        do {
            getUserMove(player[playerIdx])
            playerIdx = (playerIdx + 1) % 2
            getStatus(boardAsString())
        } while (!(status.xWins || status.oWins || status.isDraw))

        println(
            when {
                status.xWins -> "X wins"
                status.oWins -> "O wins"
                status.isDraw -> "Draw"
                else -> ""
            }
        )
    }

    override fun toString(): String {
        var boardDrawing = "---------\n"
        for (r in 1..rows) {
            boardDrawing += "| "
            for (c in 1..columns) {
                boardDrawing += "${board[r - 1][c - 1]} "
                // boardDrawing += "| "
            }
            boardDrawing += "|\n"
        }
        boardDrawing += "---------\n"

        return boardDrawing
    }

}

class CoordinatesOutOfRangeException(message: String) : Exception(message)

class OccupiedCellChosenException(message: String) : Exception(message)

fun main() {
    val tictactoe = TicTacToe()

    tictactoe.play()
}
