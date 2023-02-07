import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pawn {

    static Piece piece;

    /**
     * finds pawn's available moves
     * 
     * @param board
     * @param fromPiece
     * @param toPiece
     * @param direction
     * @param steps
     * @return
     */
    public static Piece[][] pawnMoves(Piece[][] board, Piece fromPiece, Piece toPiece, String direction, int steps) {

        if (direction == Moves.TOP.label) {
            if (steps > 0 && steps < 3) {
                int fromX = fromPiece.positionX;
                int fromY = fromPiece.positionY;
                int toX = toPiece.positionX;
                int toY = toPiece.positionY;

                // FROM WHERE THE PIECE IS MOVING

                board[fromX][fromY] = toPiece;
                if (board[fromX][fromY].label != Pieces.BLANK.label && board[fromX][fromY].moved == false) {
                    board[fromX][fromY].moved = true;
                }

                // TO WHERE THE PIECE IS MOVING

                board[toX][toY] = fromPiece;
                if (board[toX][toY].label != Pieces.BLANK.label && board[toX][toY].moved == false) {
                    board[toX][toY].moved = true;
                }

                takePositions(board, fromX, fromY, toX, toY);
            }
        } else if (direction == Moves.DIAGONAL_LEFT_BOTTOM.label || direction == Moves.DIAGONAL_RIGHT_BOTTOM.label
                || direction == Moves.DIAGONAL_LEFT_TOP.label || direction == Moves.DIAGONAL_RIGHT_TOP.label) {

            if (steps > 0 && steps < 3) {
                int fromX = fromPiece.positionX;
                int fromY = fromPiece.positionY;
                int toX = toPiece.positionX;
                int toY = toPiece.positionY;

                // FROM WHERE THE PIECE IS MOVING

                board[fromX][fromY] = new Piece(null, toX, toX, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
                ;

                // TO WHERE THE PIECE IS MOVING
                board[toX][toY] = fromPiece;
                board[toX][toY].moved = true;

                takePositions(board, fromX, fromY, toX, toY);
            }
        }
        return board;
    }

    /**
     * finds direction and steps for pawn
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @return
     */
    public static List<Integer> findDirectionAndStepsForPawn(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber) {

        // find the place from where the piece will be moved
        Piece fromPiece = Utils.fromPiece;
        fromPiece = Utils.findFrom(board, selectedPieceNumber, pieceLabel);

        // any direction thats zero means not available
        int countTotalStraightSteps = 0;
        int straightDirection = 0;
        int diagonalLeftBottom = 0;
        int diagonalRightBottom = 0;
        int diagonalLeftTop = 0;
        int diagonalRightTop = 0;

        // pawn
        if (playerName == 0) {
            // peice promotion
            // reached bottom so promote available
            if (fromPiece.positionX + 1 == board.length - 1) {
                if (board[fromPiece.positionX + 1][fromPiece.positionY].label == Pieces.BLANK.label) {
                    Utils.piecePromotionStraight = true;
                }
                if (fromPiece.positionY - 1 >= 0)
                    if (board[fromPiece.positionX + 1][fromPiece.positionY - 1].label != Pieces.BLANK.label) {
                        Utils.piecePromotionLeft = true;
                    }
                if (fromPiece.positionY + 1 < board.length)
                    if (board[fromPiece.positionX + 1][fromPiece.positionY + 1].label != Pieces.BLANK.label) {
                        Utils.piecePromotionRight = true;
                    }
            }
            // enpassant
            if (fromPiece.positionX > 1 && fromPiece.positionX < board.length - 2) {
                if (fromPiece.positionY - 1 >= 0) {
                    if (board[fromPiece.positionX][fromPiece.positionY - 1].label == Pieces.PAWN.label
                            && fromPiece.enemy != board[fromPiece.positionX][fromPiece.positionY - 1].enemy) {
                        Utils.enPassantRight = true;
                    }
                }
                if (fromPiece.positionY + 1 < board.length) {
                    if (board[fromPiece.positionX][fromPiece.positionY + 1].label == Pieces.PAWN.label
                            && fromPiece.enemy != board[fromPiece.positionX][fromPiece.positionY + 1].enemy) {
                        Utils.enPassantLeft = true;
                    }
                }
            }
        } else if (playerName == 1) {
            // reached top so promote available
            if (fromPiece.positionX - 1 == 0 && fromPiece.positionY + 1 < board.length
                    && fromPiece.positionY - 1 >= 0) {
                if (board[fromPiece.positionX - 1][fromPiece.positionY].label == Pieces.BLANK.label) {
                    Utils.piecePromotionStraight = true;
                }
                if (board[fromPiece.positionX - 1][fromPiece.positionY - 1].label != Pieces.BLANK.label) {
                    Utils.piecePromotionLeft = true;
                }
                if (board[fromPiece.positionX - 1][fromPiece.positionY + 1].label != Pieces.BLANK.label) {
                    Utils.piecePromotionRight = true;
                }
            }
            if (fromPiece.positionY + 1 < board.length) {
                if ((String) board[fromPiece.positionX][fromPiece.positionY + 1].label == (String) Pieces.PAWN.label
                        && fromPiece.enemy != board[fromPiece.positionX][fromPiece.positionY + 1].enemy) {
                    Utils.enPassantRight = true;
                }
            }
            if (fromPiece.positionY - 1 >= 0) {
                if ((String) board[fromPiece.positionX][fromPiece.positionY - 1].label == (String) Pieces.PAWN.label
                        && fromPiece.enemy != board[fromPiece.positionX][fromPiece.positionY - 1].enemy) {
                    Utils.enPassantLeft = true;
                }
            }
        }
        if (pieceLabel == Pieces.PAWN.label) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    // eat enemy down left or right for player 0
                    if (i == fromPiece.positionX + 1
                            && (j == fromPiece.positionY - 1 || j == fromPiece.positionY + 1)
                            && board[i][j].label != Pieces.BLANK.label && playerName == 0
                            && board[i][j].enemy == true) {

                        if (j == fromPiece.positionY - 1) {
                            diagonalLeftBottom = 1;
                        }
                        if (j == fromPiece.positionY + 1) {
                            diagonalRightBottom = 1;
                        }

                        Utils.printAdd(board[i][j], playerName);
                    }
                    // eat enemy upfront left or right for player 1
                    else if (i == fromPiece.positionX - 1
                            && (j == fromPiece.positionY - 1 || j == fromPiece.positionY + 1)
                            && board[i][j].label != Pieces.BLANK.label && playerName != 0
                            && board[i][j].enemy == false) {

                        if (j == fromPiece.positionY - 1) {
                            diagonalLeftTop = 1;
                        }
                        if (j == fromPiece.positionY + 1) {
                            diagonalRightTop = 1;
                        }

                        Utils.printAdd(board[i][j], playerName);

                    }
                    // move down for player 0 or move up for player 1
                    else if ((fromPiece.positionX + 1 < board.length)
                            && (board[fromPiece.positionX + 1][fromPiece.positionY].label == Pieces.BLANK.label
                                    && playerName == 0)
                            || ((fromPiece.positionX - 1 > 0)
                                    && (board[fromPiece.positionX
                                            - 1][fromPiece.positionY].label == Pieces.BLANK.label
                                            && playerName != 0))) {
                        // look straight
                        if (board[i][j].label == Pieces.BLANK.label && fromPiece.positionY == j) {
                            // look down
                            if (fromPiece.positionX < i && playerName == 0) {
                                if (fromPiece.moved == true && countTotalStraightSteps < 1) {
                                    if (i == fromPiece.positionX + 1) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);

                                    } else {
                                        System.out.print(board[i][j].label + " ");
                                    }
                                } else if (fromPiece.moved == false && countTotalStraightSteps < 2) {
                                    if (i == fromPiece.positionX + 1) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);

                                    } else if (i == fromPiece.positionX + 2) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);
                                    } else {
                                        System.out.print(board[i][j].label + " ");
                                    }
                                } else {
                                    System.out.print(board[i][j].label + " ");
                                }
                            } else if (fromPiece.positionX > i && playerName != 0) {
                                if (fromPiece.moved == true && countTotalStraightSteps < 1) {
                                    if (i == fromPiece.positionX - 1) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);

                                    } else {
                                        System.out.print(board[i][j].label + " ");
                                    }
                                } else if (fromPiece.moved == false && countTotalStraightSteps < 2) {
                                    if (i == fromPiece.positionX - 1) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);

                                    } else if (i == fromPiece.positionX - 2) {

                                        countTotalStraightSteps++;

                                        Utils.printAdd(board[i][j], playerName);
                                    } else {
                                        System.out.print(board[i][j].label + " ");
                                    }
                                } else {
                                    System.out.print(board[i][j].label + " ");
                                }
                            } else {
                                System.out.print(board[i][j].label + " ");
                            }
                        } else {
                            System.out.print(board[i][j].label + " ");
                        }
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();

        // straight direct available
        if (countTotalStraightSteps != 0) {
            straightDirection = 1;
        }
        // make a list of all moves
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsPawn.add(fromPiece);
            Utils.availableStepsPawn.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsPawn.add(new ArrayList<>(Utils.availableStepsPawn));
            } else {
                Utils.allAvailableStepsPawnAI.add(new ArrayList<>(Utils.availableStepsPawn));
            }
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsPawn.clear();
        }

        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop);
    }

    /**
     * perform enpassant if its able to
     * 
     * @param board
     * @param fromPiece
     * @param direction
     * @param playerName
     * @return
     */
    public static Piece doEnpassant(Piece[][] board, Piece fromPiece, int direction, int playerName) {
        Piece toPiece = null;
        if (playerName == 0) {
            if (direction == 19) {
                toPiece = board[fromPiece.positionX][fromPiece.positionY - 1];
            } else if (direction == 20) {
                toPiece = board[fromPiece.positionX][fromPiece.positionY + 1];
            }
        } else {
            if (direction == 19) {
                toPiece = board[fromPiece.positionX][fromPiece.positionY + 1];
            } else if (direction == 20) {
                toPiece = board[fromPiece.positionX][fromPiece.positionY - 1];
            }
        }
        return toPiece;
    }

    /**
     * perform enpassant moves in the actual board
     * 
     * @param board
     * @param fromPiece
     * @param direction
     * @param playerName
     * @return
     */

    public static Piece[][] doEnpassantMoves(Piece[][] board, Piece fromPiece, Piece toPiece, String direction,
            Integer integer, int playerName) {
        int fromX = fromPiece.positionX;
        int fromY = fromPiece.positionY;
        int toX = toPiece.positionX;
        int toY = toPiece.positionY;

        // resetting to empty spaces before castling
        board[fromX][fromY] = new Piece(null, fromX, fromY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
        board[toX][toY] = new Piece(null, toX, toY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);

        // update postions again
        if (playerName == 0) {
            toX = toPiece.positionX + 1;
        } else {
            toX = toPiece.positionX - 1;
        }

        // TO WHERE THE PIECE IS MOVING
        board[toX][toY] = fromPiece;
        board[toX][toY].moved = true;

        // updating position
        board[toX][toY].positionX = toX;
        board[toX][toY].positionY = toY;

        return board;
    }

    /**
     * Promotes a pawn whenever it reaches an end (0 or 7)
     * 
     * @param board
     * @param fromPiece
     * @param directionNumber
     * @param playerName
     * @return
     */
    public static Piece doPromote(Piece[][] board, Piece fromPiece, int directionNumber, int playerName) {
        Piece toPiece = null;
        if (playerName == 0) {
            if (directionNumber == 21) {
                toPiece = board[fromPiece.positionX + 1][fromPiece.positionY];
            } else if (directionNumber == 22) {
                toPiece = board[fromPiece.positionX + 1][fromPiece.positionY - 1];
            } else if (directionNumber == 23) {
                toPiece = board[fromPiece.positionX + 1][fromPiece.positionY + 1];
            }

        } else {
            if (directionNumber == 21) {
                toPiece = board[fromPiece.positionX - 1][fromPiece.positionY];
            } else if (directionNumber == 22) {
                toPiece = board[fromPiece.positionX - 1][fromPiece.positionY - 1];
            } else if (directionNumber == 23) {
                toPiece = board[fromPiece.positionX - 1][fromPiece.positionY + 1];
            }
        }
        return toPiece;
    }

    /**
     * perform a promotion to another piece in real board
     * 
     * @param board
     * @param fromPiece
     * @param toPiece
     * @param direction
     * @param steps
     * @param playerName
     * @return
     */
    public static Piece[][] doPromoteMoves(Piece[][] board, Piece fromPiece, Piece toPiece, String direction,
            int steps, int playerName) {
        int fromX = fromPiece.positionX;
        int fromY = fromPiece.positionY;
        int toX = toPiece.positionX;
        int toY = toPiece.positionY;

        // resetting to empty spaces before castling
        board[fromX][fromY] = new Piece(null, fromX, fromY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
        // find available pieces
        List<Integer> availablePieces = Utils.availablePieces(board, playerName);
        System.out.println("Please select the piece you want to promote.");

        // prints the available pieces on the board for both players
        for (int x = 0; x < availablePieces.size(); x++) {
            if (availablePieces.get(x) != (Integer) 0 || availablePieces.get(x) != (Integer) 5) {
                Utils.printAvailablePieces(x);
            }
        }
        System.out.println();
        int selectedPiece = Utils.input.nextInt();
        boolean enemy;
        // NEW PIECE IN MAKING
        Piece newPiece = null;
        // SETTING ENEMY
        if (playerName == 0) {
            enemy = false;
        } else {
            enemy = true;
        }
        // LIST OF AVAILABLE PIECES TO FIND NUMBER
        ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, selectedPiece, playerName);
        int number;
        do {
            // FIND A NEW NUMBER FOR THAT PIECE PARTICULARLY
            number = new Random().nextInt(10) + 1;
        } while (availablePiecesInPiece.contains(number));
        if (selectedPiece == 1) {
            newPiece = new Piece(false, toX, toY, Pieces.QUEEN.label, false, Power.QUEEN.power, enemy, number);
        } else if (selectedPiece == 2) {
            newPiece = new Piece(false, toX, toY, Pieces.KNIGHT.label, false, Power.KNIGHT.power, enemy, number);
        } else if (selectedPiece == 3) {
            newPiece = new Piece(false, toX, toY, Pieces.BISHOP.label, false, Power.BISHOP.power, enemy, number);
        } else if (selectedPiece == 4) {
            newPiece = new Piece(false, toX, toY, Pieces.ROOK.label, false, Power.ROOK.power, enemy, number);
        }

        // TO WHERE THE PIECE IS MOVING MAKE A NEW PIECE
        board[toX][toY] = newPiece;

        // MAKE FROM PIECE EMPTY
        board[fromX][fromY] = new Piece(null, fromX, fromY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);

        return board;
    }

    private static void takePositions(Piece[][] board, int fromX, int fromY, int toX, int toY) {
        board[fromX][fromY].positionX = fromX;
        board[fromX][fromY].positionY = fromY;
        board[toX][toY].positionX = toX;
        board[toX][toY].positionY = toY;
    }
}
