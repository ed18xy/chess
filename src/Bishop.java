import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop {

    /**
     * finds all bishop's directions and steps for that direction
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @param enemy
     * @return
     */
    public static List<Integer> findDirectionAndStepsForBishop(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber, Boolean enemy) {

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

        // diagonalLeftTop
        int x = Utils.fromPiece.positionX - 1;
        int y = Utils.fromPiece.positionY - 1;
        while (x >= 0 && y >= 0) {
            if (board[x][y].label == Pieces.BLANK.label) {
                diagonalLeftTop++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

            } else if (board[x][y].enemy == enemy && board[x][y].label != Pieces.BLANK.label) {
                break;
            } else if (board[x][y].label != Pieces.BLANK.label && board[x][y].enemy != enemy) {
                diagonalLeftTop++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

                break;
            }
            x--;
            y--;
        }

        // diagonalRightTop
        x = Utils.fromPiece.positionX - 1;
        y = Utils.fromPiece.positionY + 1;
        while (x >= 0 && y < board.length) {
            if (board[x][y].label == Pieces.BLANK.label) {
                diagonalRightTop++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

            } else if (board[x][y].enemy == enemy && board[x][y].label != Pieces.BLANK.label) {
                break;
            } else if (board[x][y].label != Pieces.BLANK.label && board[x][y].enemy != enemy) {
                diagonalRightTop++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

                break;
            }
            x--;
            y++;
        }

        // diagonalLeftBottom
        x = Utils.fromPiece.positionX + 1;
        y = Utils.fromPiece.positionY - 1;
        while (x < board.length && y >= 0) {
            if (board[x][y].label == Pieces.BLANK.label) {
                diagonalLeftBottom++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

            } else if (board[x][y].enemy == enemy && board[x][y].label != Pieces.BLANK.label) {
                break;
            } else if (board[x][y].label != Pieces.BLANK.label && board[x][y].enemy != enemy) {
                diagonalLeftBottom++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

                break;
            }
            x++;
            y--;
        }

        // diagonalRightBottom
        x = Utils.fromPiece.positionX + 1;
        y = Utils.fromPiece.positionY + 1;
        while (x < board.length && y < board.length) {
            if (board[x][y].label == Pieces.BLANK.label) {
                diagonalRightBottom++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

            } else if (board[x][y].enemy == enemy && board[x][y].label != Pieces.BLANK.label) {
                break;
            } else if (board[x][y].label != Pieces.BLANK.label && board[x][y].enemy != enemy) {
                diagonalRightBottom++;

                // add to available list
                if (playerName == 0 || playerName == 2) {
                    Utils.allAvailablePiecesSteps.add(board[x][y]);
                }

                break;
            }
            x++;
            y++;
        }

        // adding all moves in a list
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsBishop.add(Utils.fromPiece);
            Utils.availableStepsBishop.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsBishop.add(new ArrayList<>(Utils.availableStepsBishop));
            } else {
                Utils.allAvailableStepsBishopAI.add(new ArrayList<>(Utils.availableStepsBishop));
            }
            // clearing previous lists
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsBishop.clear();
        }

        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop, topRight1, topRight2, rightDown1, rightDown2, downRight1, downRight2,
                leftRight1, leftRight2);
    }
}
