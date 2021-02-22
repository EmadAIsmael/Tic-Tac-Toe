package tictactoe


class TicTacToe {
    val rows = 3
    val columns = 3
    val board = Array(3) { arrayOf("_", "_", "_") }

    fun setX(row: Int, col: Int) {
        board[row-1][col-1] = "X"
    }

    fun setO(row: Int, col: Int) {
        board[row-1][col-1] = "O"
    }

    fun drawUserRep(str: String) {
        for (r in 1..3)
            for (c in 1..3)
                board[r-1][c-1] = str[((r-1)*3)+c-1].toString()
        println(this)
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

fun main() {
    val tictactoe = TicTacToe()

//    tictactoe.setX(1, 2)
//    tictactoe.setX(2, 3)
//
//    println(tictactoe)
    print("Enter cells: ")
    val cells = readLine()!!
    tictactoe.drawUserRep(cells)
}