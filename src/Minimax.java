import java.util.ArrayList;
import java.util.List;

public class Minimax {
    static int finalPly = 4;
    static Piece[][] decision;

    /**
     * Returns the mive that AI needs to take that is the best according to minimax
     * algorithm evaluation
     * 
     * @param board
     * @param playerName
     * @return
     */
    public static Piece[][] moves(Piece[][] board, int playerName) {
        minimaxDecision(board);
        return decision;
    }

    /**
     * Makes decision for the next move based on minimax algorithm
     * 
     * @param board
     */
    private static void minimaxDecision(Piece[][] board) {
        max(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    /**
     * Chooses minimum value available for the move
     * 
     * @param board
     * @param alpha
     * @param beta
     * @param ply
     * @return
     */
    private static int min(Piece[][] board, int alpha, int beta, int ply) {
        if (ply >= finalPly) {
            return Heuristic.calculateHeuristic(board); // evaluate heuristic for the board
        }
        int bestMove = Integer.MAX_VALUE;
        List<Piece[][]> al = new ArrayList<Piece[][]>();
        for (Piece[][] p : successorPlayer(board)) {
            al.add(p);
        }
        for (int i = 0; i < al.size(); i++) {
            Piece[][] copyBoard = Utils.copyBoard(al.get(i));
            bestMove = Math.min(max(copyBoard, alpha, beta, ply + 1), bestMove);
            // Board.printBoardManual(copyBoard);
            // prunning
            if (bestMove <= alpha)
                return bestMove;
            beta = Math.min(beta, bestMove);
        }
        return bestMove;
    }

    /**
     * Chooses maximum value available for the move
     * 
     * @param board
     * @param alpha
     * @param beta
     * @param ply
     * @return
     */
    private static int max(Piece[][] board, int alpha, int beta, int ply) {
        if (ply >= finalPly) {
            return Heuristic.calculateHeuristic(board); // evaluate heuristic for the board
        }
        int bestMove = Integer.MIN_VALUE;
        List<Piece[][]> al = new ArrayList<Piece[][]>();
        for (Piece[][] p : successorAI(board)) {
            al.add(p);
        }
        for (int i = 0; i < al.size(); i++) {
            int v = Math.max(min(al.get(i), alpha, beta, ply + 1), bestMove);
            // Board.printBoardManual(copyBoard);
            if(bestMove<v){
                bestMove = v;
                if(ply == 0)decision = al.get(i).clone();//if we get a better move, updates AI's decision
            }
            // prunning
            if (bestMove >= beta)
                return bestMove;
            alpha = Math.max(alpha, bestMove);
        }
        return bestMove;
    }

    /**
     * Returns list of next moves available for the board that AI can take
     * 
     * @param board
     * @return
     */
    private static ArrayList<Piece[][]> successorAI(Piece[][] board) {
        try {
            AI.moves(board, 2);
        } catch (CloneNotSupportedException c) {
            c.printStackTrace();
        }
        return AI.allMovedBoardAI;
    }

    /**
     * Returns list of next moves available for the board that player can take
     * 
     * @param board
     * @return
     */
    private static ArrayList<Piece[][]> successorPlayer(Piece[][] board) {
        try {
            AI.moves(board, 0);
        } catch (CloneNotSupportedException c) {
            c.printStackTrace();
        }
        return AI.allMovedBoard;
    }

}
