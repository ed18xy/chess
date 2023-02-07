public class Heuristic {
    // Weights for different heuristics
    static int materialW = 5;
    static int materialopponentW = -5;
    static int availMovesW = 2;
    static int availMovesopponentW = -2;
    static int checkStatusW = 10;
    static int checkStatusopponentW = -10;

    /**
     * Returns the heuristic value for the board
     * 
     * @param board
     * @return
     */
    public static int calculateHeuristic(Piece[][] board) {

        return materialW * material(board, 2)
                + materialopponentW * material(board, 0)
                + availMovesW * availMoves(board, 2)
                + availMovesopponentW * availMoves(board, 0)
                + checkStatusW * checkStatus(board, 2)
                + checkStatusopponentW * checkStatus(board, 0)
                + Evaluator.evaluateBoard(board, 2)/1000
                + Evaluator.evaluateBoard(board, 0)/1000;
    }

    /**
     * Evaluates board based on pieces available, in favor of keeping more pieces
     * and destroying opponent's pieces
     * 
     * @param board
     * @param player
     * @return
     */
    static private int material(Piece[][] board, int player) {
        int totalScore = 0;
        boolean evalOpponent;
        if (player == 0) {// check for opponent
            evalOpponent = false;
        } else {// check for AI
            evalOpponent = true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].label != "-") {
                    if (board[i][j].enemy == evalOpponent) {
                        if (board[i][j].label == Pieces.PAWN.label) {
                            totalScore += 1;
                        } else if (board[i][j].label == Pieces.BISHOP.label) {
                            totalScore += 3;
                        } else if (board[i][j].label == Pieces.KNIGHT.label) {
                            totalScore += 3;
                        } else if (board[i][j].label == Pieces.QUEEN.label) {
                            totalScore += 8;
                        } else if (board[i][j].label == Pieces.ROOK.label) {
                            totalScore += 5;
                        }
                    }
                }
            }
        }
        return totalScore;
    }

    /**
     * Evaluates board based on how many moves can AI/Player take afterwards, in
     * favor of dominating the board
     * 
     * @param board
     * @param player
     * @return
     */
    static private int availMoves(Piece[][] board, int player) {
        try {
            AI.moves(board, player);
        } catch (CloneNotSupportedException c) {
            c.printStackTrace();
        }
        if (player == 2)
            return AI.allMovedBoardAI.size();
        else
            return AI.allMovedBoard.size();
    }

    /**
     * Evaluates board based on if AI/Opponent's king is safe and there is no
     * checkmate
     * 
     * @param board
     * @param player
     * @return
     */
    static private int checkStatus(Piece[][] board, int player) {
        if (!King.checkKingSafe(board, player))
            return 5;
        else
            return -5;
    }
}
