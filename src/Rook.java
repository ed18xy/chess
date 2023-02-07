import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook {

    /**
     * finds directions and steps for rook
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @param enemy
     * @return
     */
    public static List<Integer> findDirectionAndStepsForRook(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber, Boolean enemy) {
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
        int bottom = 0;
        int left = 0;
        int right = 0;

        // left direction
        int x = Utils.fromPiece.positionX;
        int y = Utils.fromPiece.positionY - 1;
        while (y >= 0) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            left += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            y--;
        }

        // right direction
        x = Utils.fromPiece.positionX;
        y = Utils.fromPiece.positionY + 1;
        while (y < board.length) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            right += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            y++;
        }

        // bottom direction
        x = Utils.fromPiece.positionX + 1;
        y = Utils.fromPiece.positionY;
        while (x < board.length) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            bottom += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x++;
        }

        // straight direction
        x = Utils.fromPiece.positionX - 1;
        y = Utils.fromPiece.positionY;
        while (x >= 0) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            straightDirection += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x--;
        }

        // add all moves to a list
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsRook.add(Utils.fromPiece);
            Utils.availableStepsRook.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsRook.add(new ArrayList<>(Utils.availableStepsRook));
            } else {
                Utils.allAvailableStepsRookAI.add(new ArrayList<>(Utils.availableStepsRook));
            }
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsRook.clear();
        }
        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop, topRight1, topRight2, rightDown1, rightDown2, downRight1, downRight2,
                leftRight1, leftRight2, bottom, left, right);
    }

}
