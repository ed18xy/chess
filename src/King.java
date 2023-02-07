import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King {

    /**
     * find direction and steps for a king piece
     * 
     * @param board
     * @param pieceLabel
     * @param playerName
     * @param selectedPieceNumber
     * @param enemy
     * @return
     */
    public static List<Integer> findDirectionAndStepsForKing(Piece[][] board, String pieceLabel, int playerName,
            int selectedPieceNumber, Boolean enemy) {

        // find from where the piece is moving
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

        int x = Utils.fromPiece.positionX;
        int y = Utils.fromPiece.positionY;

        // look for all positions able to be moved
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == x - 1 && j == y) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        straightDirection = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x - 1 && j == y + 1) {
                    if (board[i][j].enemy != enemy) {
                        {
                            printKing(board[i][j]);
                            diagonalRightTop = 1;
                        }
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x && j == y + 1) {
                    if (board[i][j].enemy != enemy) {
                        {
                            printKing(board[i][j]);
                            right = 1;
                        }
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x + 1 && j == y + 1) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        diagonalRightBottom = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x + 1 && j == y) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        bottom = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x + 1 && j == y - 1) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        diagonalLeftBottom = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x && j == y - 1) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        left = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else if (i == x - 1 && j == y - 1) {
                    if (board[i][j].enemy != enemy) {
                        printKing(board[i][j]);
                        diagonalLeftTop = 1;
                    } else {
                        System.out.print(board[i][j].label + " ");
                    }
                } else {
                    System.out.print(board[i][j].label + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        // castling
        if (Utils.fromPiece.moved == false) {
            Utils.castleRight = checkCastlingRight(Utils.fromPiece, board);
            Utils.castleLeft = checkCastlingLeft(Utils.fromPiece, board);
        }

        // add all availbale moves in a list
        if (Utils.allAvailablePiecesSteps.size() > 0) {
            Utils.availableStepsKing.add(Utils.fromPiece);
            Utils.availableStepsKing.addAll(Utils.allAvailablePiecesSteps);
            if (playerName == 0) {
                Utils.allAvailableStepsKing.add(new ArrayList<>(Utils.availableStepsKing));
            } else {
                Utils.allAvailableStepsKingAI.add(new ArrayList<>(Utils.availableStepsKing));
            }
            Utils.allAvailablePiecesSteps.clear();
            Utils.availableStepsKing.clear();
        }

        return Arrays.asList(countTotalStraightSteps, straightDirection, diagonalLeftBottom, diagonalRightBottom,
                diagonalLeftTop, diagonalRightTop, topRight1, topRight2, rightDown1, rightDown2, downRight1, downRight2,
                leftRight1, leftRight2, bottom, left, right);
    }

    /**
     * prints a king's position
     * 
     * @param piece
     */
    private static void printKing(Piece piece) {
        if (piece.label != Pieces.BLANK.label) {
            System.out.print(ConsoleColors.BLUE + "x " + ConsoleColors.RESET);
        } else {
            System.out.print(ConsoleColors.YELLOW + "x " + ConsoleColors.RESET);
        }
    }

    /**
     * checks if castling right can be done
     * 
     * @param fromPiece
     * @param board
     * @return
     */
    private static boolean checkCastlingRight(Piece fromPiece, Piece[][] board) {
        if (board[fromPiece.positionX][board.length - 1].label == Pieces.ROOK.label
                && !board[fromPiece.positionX][board.length - 1].moved
                && board[fromPiece.positionX][0].enemy == fromPiece.enemy) {
            for (int j = fromPiece.positionY + 1; j < board.length - 1; j++) {
                if (board[fromPiece.positionX][j].label != Pieces.BLANK.label) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * check if castling can be done in left side
     * 
     * @param fromPiece
     * @param board
     * @return
     */
    private static boolean checkCastlingLeft(Piece fromPiece, Piece[][] board) {
        if (board[fromPiece.positionX][0].label == Pieces.ROOK.label
                && !board[fromPiece.positionX][0].moved && board[fromPiece.positionX][0].enemy == fromPiece.enemy) {
            for (int j = fromPiece.positionY - 1; j > 0; j--) {
                if (board[fromPiece.positionX][j].label != Pieces.BLANK.label) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * returns the place from where castling is to be done
     * 
     * @param board
     * @param fromPiece
     * @param direction
     * @param playerName
     * @return
     */
    public static Piece doCastling(Piece[][] board, Piece fromPiece, String direction, int playerName) {
        Piece toPiece = null;
        if (direction == Moves.LEFT_CASTLE.label) {
            toPiece = board[fromPiece.positionX][0];
        } else if (direction == Moves.RIGHT_CASTLE.label) {
            toPiece = board[fromPiece.positionX][board.length - 1];
        }
        return toPiece;
    }

    /**
     * performs an actual castling move
     * 
     * @param board
     * @param fromPiece
     * @param toPiece
     * @param direction
     * @param integer
     * @param playerName
     * @return
     */
    public static Piece[][] doCastlingMoves(Piece[][] board, Piece fromPiece, Piece toPiece, String direction,
            Integer integer, Integer playerName) {
        int fromX = fromPiece.positionX;
        int fromY = fromPiece.positionY;
        int toX = toPiece.positionX;
        int toY = toPiece.positionY;

        // resetting to empty spaces before castling
        board[fromX][fromY] = new Piece(null, fromX, fromY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
        board[toX][toY] = new Piece(null, toX, toY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);

        // update postions again
        if (direction == Moves.RIGHT_CASTLE.label) {
            fromY = fromPiece.positionY + 1;
            toY = toPiece.positionY - 1;
        } else {
            fromY = fromPiece.positionY - 2;
            toY = toPiece.positionY + 1;
        }

        // FROM WHERE THE PIECE IS MOVING
        board[fromX][fromY] = toPiece;
        board[fromX][fromY].moved = true;

        // TO WHERE THE PIECE IS MOVING
        board[toX][toY] = fromPiece;
        board[toX][toY].moved = true;

        // updating position
        board[fromX][fromY].positionX = fromX;
        board[fromX][fromY].positionY = fromY;

        board[toX][toY].positionX = toX;
        board[toX][toY].positionY = toY;

        return board;
    }

    /**
     * checks if a king is safe from checkmate
     * 
     * @param board
     * @param playerName
     * @return
     */
    public static boolean checkKingSafe(Piece[][] board, int playerName) {
        Piece king = findKing(board, playerName);
        if (king == null) {
            return false;
        }
        if (checkLeft(board, king)) {
            return true;
        }
        if (checkRight(board, king)) {
            return true;
        }
        if (checkTop(board, king)) {
            return true;
        }
        if (checkBottom(board, king)) {
            return true;
        }
        if (checkLeftTopCorner(board, king, playerName)) {
            return true;
        }
        if (checkRightTopCorner(board, king, playerName)) {
            return true;
        }
        if (checkLeftBottomCorner(board, king, playerName)) {
            return true;
        }
        if (checkRightBottomCorner(board, king, playerName)) {
            return true;
        }
        if (checkKnight(board, king)) {
            return true;
        }
        return false;
    }

    /**
     * checks if the piece is a knight
     * 
     * @param posX
     * @param posY
     * @param board
     * @param king
     * @return
     */
    private static boolean forKnight(int posX, int posY, Piece[][] board, Piece king) {
        return ((board[posX][posY].label == Pieces.KNIGHT.label) && (board[posX][posY].enemy != king.enemy));
    };

    /**
     * checks checkmate for knight
     * 
     * @param board
     * @param king
     * @return
     */
    private static boolean checkKnight(Piece[][] board, Piece king) {
        int posX = king.positionX - 1;
        int posY = king.positionY + 2;
        boolean checkMate = false;

        // check for 8 positions of a knight
        if (posX >= 0 && posY < board.length) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX - 2;
        posY = king.positionY + 1;
        if (posX >= 0 && posY < board.length) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX + 1;
        posY = king.positionY + 2;
        if (posX < board.length && posY < board.length) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX + 2;
        posY = king.positionY + 1;
        if (posX < board.length && posY < board.length) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX + 2;
        posY = king.positionY - 1;
        if (posX < board.length && posY >= 0) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX + 1;
        posY = king.positionY - 2;
        if (posX < board.length && posY >= 0) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX - 1;
        posY = king.positionY - 2;
        if (posX >= 0 && posY >= 0) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        posX = king.positionX - 2;
        posY = king.positionY - 1;
        if (posX >= 0 && posY >= 0) {
            checkMate = forKnight(posX, posY, board, king);
            if (checkMate) {
                return checkMate;
            }
        }
        return checkMate;
    }

    /**
     * checks if any enemy pieces available left top corner
     * queen or bishop
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkLeftTopCorner(Piece[][] board, Piece king, int playerName) {
        int count = 0;
        for (int i = king.positionX - 1; i >= 0; i--) {
            if (count < 1 && playerName == 1) {
                if ((board[i][i].label == Pieces.PAWN.label)
                        && board[i][i].enemy != king.enemy) {
                    return true;
                }
            }
            count++;
            if ((board[i][i].label == Pieces.BISHOP.label
                    || board[i][i].label == Pieces.QUEEN.label)
                    && board[i][i].enemy != king.enemy) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * checks if any enemy pieces available left bottom corner
     * queen or bishop
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkLeftBottomCorner(Piece[][] board, Piece king, int playerName) {
        int i = king.positionX + 1;
        int j = king.positionY - 1;
        int count = 0;

        while (i < board.length && j >= 0) {
            if (count < 1 && playerName == 0) {
                if ((board[i][j].label == Pieces.PAWN.label)
                        && board[i][j].enemy != king.enemy) {
                    return true;
                }
            }
            count++;
            if ((board[i][j].label == Pieces.BISHOP.label
                    || board[i][j].label == Pieces.QUEEN.label)
                    && board[i][j].enemy != king.enemy) {
                return true;
            } else if (board[i][j].label != Pieces.BLANK.label) {
                return false;
            }
            i++;
            j--;
        }
        return false;
    }

    /**
     * checks if any enemy pieces available right top corner
     * queen or bishop
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkRightTopCorner(Piece[][] board, Piece king, int playerName) {
        int i = king.positionX - 1;
        int j = king.positionY + 1;
        int count = 0;

        while (i >= 0 && j < board.length) {
            if (count < 1 && playerName == 1) {
                if ((board[i][j].label == Pieces.PAWN.label)
                        && board[i][j].enemy != king.enemy) {
                    return true;
                }
            }
            count++;
            if ((board[i][j].label == Pieces.BISHOP.label
                    || board[i][j].label == Pieces.QUEEN.label)
                    && board[i][j].enemy != king.enemy) {
                return true;
            } else if (board[i][j].label != Pieces.BLANK.label) {
                return false;
            }
            i--;
            j++;
        }
        return false;
    }

    /**
     * checks if any enemy pieces available right bottom corner
     * queen or bishop
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkRightBottomCorner(Piece[][] board, Piece king, int playerName) {
        int count = 0;
        for (int i = king.positionX + 1; i < board.length; i++) {
            if (count < 1 && playerName == 0) {
                if ((board[i][i].label == Pieces.PAWN.label)
                        && board[i][i].enemy != king.enemy) {
                    return true;
                }
            }
            count++;
            if ((board[i][i].label == Pieces.BISHOP.label
                    || board[i][i].label == Pieces.QUEEN.label)
                    && board[i][i].enemy != king.enemy) {
                return true;
            } else if (board[i][i].label != Pieces.BLANK.label) {
                return false;
            }
        }
        return false;
    }

    /**
     * checks if any enemy pieces available bottom
     * queen or rook or pawn
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkBottom(Piece[][] board, Piece king) {
        for (int i = king.positionX + 1; i < board.length; i++) {
            if ((board[i][king.positionY].label == Pieces.ROOK.label
                    || board[i][king.positionY].label == Pieces.QUEEN.label)
                    && board[i][king.positionY].enemy != king.enemy) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * checks if any enemy pieces available top
     * queen or rook or pawn
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkTop(Piece[][] board, Piece king) {
        for (int i = king.positionX - 1; i >= 0; i--) {
            if ((board[i][king.positionY].label == Pieces.ROOK.label
                    || board[i][king.positionY].label == Pieces.QUEEN.label)
                    && board[i][king.positionY].enemy != king.enemy) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * checks if any enemy pieces available left
     * queen or rook or pawn
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkLeft(Piece[][] board, Piece king) {
        for (int i = king.positionY - 1; i >= 0; i--) {
            if ((board[king.positionX][i].label == Pieces.ROOK.label
                    || board[king.positionX][i].label == Pieces.QUEEN.label)
                    && board[king.positionX][i].enemy != king.enemy) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * checks if any enemy pieces available right
     * queen or rook or pawn
     * 
     * @param board
     * @param king
     * @param playerName
     * @return
     */
    private static boolean checkRight(Piece[][] board, Piece king) {
        for (int i = king.positionY + 1; i < board.length; i++) {
            if ((board[king.positionX][i].label == Pieces.ROOK.label
                    || board[king.positionX][i].label == Pieces.QUEEN.label)
                    && board[king.positionX][i].enemy != king.enemy) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * finds a replacing king
     * 
     * @param board
     * @param playerName
     * @return
     */
    private static Piece findKing(Piece[][] board, int playerName) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (playerName == 0) {
                    if (board[i][j].label != Pieces.BLANK.label) {
                        if (board[i][j].enemy == false && board[i][j].label == Pieces.KING.label) {
                            return board[i][j];
                        }
                    }
                } else if (playerName != 0) {
                    if (board[i][j].label != Pieces.BLANK.label) {
                        if (board[i][j].enemy == true && board[i][j].label == Pieces.KING.label) {
                            return board[i][j];
                        }
                    }
                }
            }
        }
        return null;
    }
}
