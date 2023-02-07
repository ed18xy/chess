public class Evaluator {

    private static final int[][] PawnTable = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 50, 50, 50, 50, 50, 50, 50, 50 },
            { 10, 10, 20, 30, 30, 20, 10, 10 },
            { 5, 5, 10, 27, 27, 10, 5, 5 },
            { 0, 0, 0, 25, 25, 0, 0, 0 },
            { 5, -5, -10, 0, 0, -10, -5, 5 },
            { 5, 10, 10, -25, -25, 10, 10, 5 },
            { 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    private static final int[][] KnightTable = {
            { -50, -40, -30, -30, -30, -30, -40, -50 },
            { -40, -20, 0, 0, 0, 0, -20, -40 },
            { -30, 0, 10, 15, 15, 10, 0, -30 },
            { -30, 5, 15, 20, 20, 15, 5, -30 },
            { -30, 0, 15, 20, 20, 15, 0, -30 },
            { -30, 5, 10, 15, 15, 10, 5, -30 },
            { -40, -20, 0, 5, 5, 0, -20, -40 },
            { -50, -40, -20, -30, -30, -20, -40, -50 }
    };

    private static final int[][] BishopTable = {
            { -20, -10, -10, -10, -10, -10, -10, -20 },
            { -10, 0, 0, 0, 0, 0, 0, -10 },
            { -10, 0, 5, 10, 10, 5, 0, -10 },
            { -10, 5, 5, 10, 10, 5, 5, -10 },
            { -10, 0, 10, 10, 10, 10, 0, -10 },
            { -10, 10, 10, 10, 10, 10, 10, -10 },
            { -10, 5, 0, 0, 0, 0, 5, -10 },
            { -20, -10, -40, -10, -10, -40, -10, -20 }
    };

    private static final int[][] KingTable = {
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { -20, -30, -30, -40, -40, -30, -30, -20 },
            { -10, -20, -20, -20, -20, -20, -20, -10 },
            { 20, 20, 0, 0, 0, 0, 20, 20 },
            { 20, 30, 10, 0, 0, 10, 30, 20 }
    };

    private static final int[][] RookTable = {
            { -50, -40, -30, -30, -30, -30, -40, -50 },
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { -30, -40, -40, -50, -50, -40, -40, -30 },
            { 5, 5, 10, 27, 27, 10, 5, 5 },
            { 0, 0, 0, 25, 25, 0, 0, 0 },
            { 5, -5, -10, 0, 0, -10, -5, 5 },
            { 20, 20, 0, 0, 0, 0, 20, 20 },
            { 20, 30, 10, 0, 0, 10, 30, 20 }
    };

    /**
     * Evalutes a ChessBoard based on positions of different pieces and returns the score it received.
     * 
     * @param piece
     * @param playerName
     * @return
     */
    public static int evaluateBoard(Piece[][] piece, int playerName) {

        int score = 0;
        int totalScore = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (piece[i][j].label != Pieces.BLANK.label) {
                    int posX = j;
                    int posY = i;
                    if (playerName == 2) {
                        posX = 7 - j;
                        posY = 7 - i;
                    }

                    score += piece[i][j].power;

                    if (piece[i][j].label == Pieces.BISHOP.label) {
                        score += BishopTable[posY][posX];
                    } else if (piece[i][j].label == Pieces.KNIGHT.label) {
                        score += KnightTable[posY][posX];
                    } else if (piece[i][j].label == Pieces.PAWN.label) {
                        score += PawnTable[posY][posX];
                    } else if (piece[i][j].label == Pieces.ROOK.label) {
                        score += RookTable[posY][posX];
                    } else if (piece[i][j].label == Pieces.QUEEN.label) {
                        if (piece[i][j].moved) {
                            score -= 10;
                        }
                    } else if (piece[i][j].label == Pieces.KING.label) {
                        score += KingTable[posY][posX];
                    }

                    if (playerName == 2) {
                        totalScore += score;
                        score = 0;
                    } else {
                        totalScore -= score;
                        score = 0;
                    }
                }
            }
        }
        return totalScore;
    }
}
