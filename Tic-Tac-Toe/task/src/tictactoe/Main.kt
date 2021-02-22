package tictactoe


class TicTacToe {
    val rows = 3
    val columns = 3
    val board = Array(3) { arrayOf("O", "O", "O") }

    fun setX(row: Int, col: Int) {
        board[row-1][col-1] = "X"
    }

    fun setO(row: Int, col: Int) {
        board[row-1][col-1] = "O"
    }

    override fun toString(): String {
        var boardDrawing = ""     // " _ _ _\n"
        for (r in 1..rows) {
            // boardDrawing += "|"
            for (c in 1..columns) {
                boardDrawing += "${board[r - 1][c - 1]} "
                // boardDrawing += "|"
            }
            boardDrawing += "\n"
        }
        // boardDrawing += " - - -"

        return boardDrawing
    }

}

fun main() {
    val tictactoe = TicTacToe()

    tictactoe.setX(1, 2)
    tictactoe.setX(2, 3)

    println(tictactoe)
}