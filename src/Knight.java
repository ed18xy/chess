import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight {

    /**
     * finds all knight's directions and steps for that direction
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @param isEnemy
     * @return
     */
    public static List<Integer> findDirectionAndStepsForKnight(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber, Boolean isEnemy) {

        // find the place from where the piece will be moved
        Utils.fromPiece = Utils.findFrom(board, selectedPieceNumber, pieceLabel);

        int countTotalStraightSteps = 0;
        int straightDirection = 0;
        int diagonalLeftBottom = 0;
        int diagonalRightBottom = 0;
        int diagonalLeftTop = 0;
        int diagonalRightTop = 0;
        int topRight1 = 0;
        int topRight2 = 0;
        int rightDown1 = 0;
        int rightDown2 = 0;
        int downRight1 = 0;
        int downRight2 = 0;
        int leftRight1 = 0;
        int leftRight2 = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // topRight1
                if (i == Utils.fromPiece.positionX - 2 && j == Utils.fromPiece.positionY + 1
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {

                    topRight1 = 1;
                    Utils.printAdd(board[i][j], playerName);

                }
                // topRight2
                else if (i == Utils.fromPiece.positionX - 1 && j == Utils.fromPiece.positionY + 2
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {

                    topRight2 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }
                // rightDown1
                else if (i == Utils.fromPiece.positionX + 1 && j == Utils.fromPiece.positionY + 2
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {

                    rightDown1 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }
                // rightDown2
                else if (i == Utils.fromPiece.positionX + 2 && j == Utils.fromPiece.positionY + 1
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {
                    rightDown2 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }

                // downRight1
                else if (i == Utils.fromPiece.positionX + 2 && j == Utils.fromPiece.positionY - 1
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {
                    downRight1 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }

                // downRight2
                else if (i == Utils.fromPiece.positionX + 1 && j == Utils.fromPiece.positionY - 2
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {
                    downRight2 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }

                // leftRight1
                else if (i == Utils.fromPiece.positionX - 1 && j == Utils.fromPiece.positionY - 2
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {
                    leftRight1 = 1;
                    Utils.printAdd(board[i][j], playerName);
                }

                // leftRight2
                else if (i == Utils.fromPiece.positionX - 2 && j == Utils.fromPiece.positionY - 1
                        && (board[i][j].enemy != isEnemy
                                || board[i][j].enemy == null)) {

                    leftRight2 = 1;
                    Utils.printAdd(board[i][j], playerName);
                } else {
                    System.out.print(board[i][j].label + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        // adding all moves in a list
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsKnight.add(Utils.fromPiece);
            Utils.availableStepsKnight.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsKnight.add(new ArrayList<>(Utils.availableStepsKnight));
            } else {
                Utils.allAvailableStepsKnightAI.add(new ArrayList<>(Utils.availableStepsKnight));
            }
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsKnight.clear();
        }

        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop, topRight1, topRight2, rightDown1, rightDown2, downRight1, downRight2,
                leftRight1, leftRight2);
    }
}
