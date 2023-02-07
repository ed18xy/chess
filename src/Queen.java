import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen {

    /**
     * finds directions and steps for queen
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @param enemy
     * @return
     */
    public static List<Integer> findDirectionAndStepsForQueen(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber, Boolean enemy) {

        // find the position from where queen is moving
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

        // diagonal left top
        x = Utils.fromPiece.positionX - 1;
        y = Utils.fromPiece.positionY - 1;
        while (x >= 0 && y >= 0) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            diagonalLeftTop += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x--;
            y--;
        }

        // diagonal right top
        x = Utils.fromPiece.positionX - 1;
        y = Utils.fromPiece.positionY + 1;
        while (x >= 0 && y < board.length) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            diagonalRightTop += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x--;
            y++;
        }

        // diagonal left bottom
        x = Utils.fromPiece.positionX + 1;
        y = Utils.fromPiece.positionY - 1;
        while (x < board.length && y >= 0) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            diagonalLeftBottom += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x++;
            y--;
        }

        // diagonal right bottom
        x = Utils.fromPiece.positionX + 1;
        y = Utils.fromPiece.positionY + 1;
        while (x < board.length && y < board.length) {
            int[] findCounts = Utils.stepsCount(board[x][y], playerName, enemy);
            diagonalRightBottom += findCounts[1];
            if (findCounts[0] == 0) {
                break;
            }
            x++;
            y++;
        }

        // add all available moves
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsQueen.add(Utils.fromPiece);
            Utils.availableStepsQueen.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsQueen.add(new ArrayList<>(Utils.availableStepsQueen));
            } else {
                Utils.allAvailableStepsQueenAI.add(new ArrayList<>(Utils.availableStepsQueen));
            }
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsQueen.clear();
        }

        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop, topRight1, topRight2, rightDown1, rightDown2, downRight1, downRight2,
                leftRight1, leftRight2, bottom, left, right);
    }
}
