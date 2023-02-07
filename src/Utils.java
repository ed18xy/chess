import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static Scanner input = new Scanner(System.in);
    public static Piece fromPiece, toPiece, usePiece;
    public static String[] allPieces = { "K", "Q", "B", "N", "R", "P" };
    public static boolean castleRight = false;
    public static boolean castleLeft = false;
    public static boolean enPassantRight = false;
    public static boolean enPassantLeft = false;
    public static boolean piecePromotionStraight = false;
    public static boolean piecePromotionLeft = false;
    public static boolean piecePromotionRight = false;
    public static ArrayList<Piece> allAvailablePiecesSteps = new ArrayList<>();
    public static Piece kingPlayer0;
    public static Piece kingPlayer1;
    public static Piece kingPlayer2;

    public static ArrayList<Piece> availableStepsPawn = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsPawn = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsPawnAI = new ArrayList<>();

    public static ArrayList<Piece> availableStepsRook = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsRook = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsRookAI = new ArrayList<>();

    public static ArrayList<Piece> availableStepsKnight = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsKnight = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsKnightAI = new ArrayList<>();

    public static ArrayList<Piece> availableStepsBishop = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsBishop = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsBishopAI = new ArrayList<>();

    public static ArrayList<Piece> availableStepsQueen = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsQueen = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsQueenAI = new ArrayList<>();

    public static ArrayList<Piece> availableStepsKing = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsKing = new ArrayList<>();
    public static ArrayList<ArrayList<Piece>> allAvailableStepsKingAI = new ArrayList<>();

    /**
     * gets player inputs and pieces directions with steps
     * 
     * @param board
     * @param playerName
     * @return
     */
    public static List<Integer> getPlayerInputs(Piece[][] board, int playerName) {

        List<Integer> findDirectionAndSteps = null;

        int selectedPiece;
        int selectedPieceNumber;
        int steps = 0;
        int direction;

        System.out.println("Player-" + playerName + " please choose which piece you want to move-");
        System.out.println();

        System.out.println("Your available pieces are:");

        // find available pieces
        List<Integer> availablePieces = availablePieces(board, playerName);

        // prints the available pieces on the board for both players
        for (int x = 0; x < availablePieces.size(); x++) {
            if (availablePieces.get(x) != 0) {
                printAvailablePieces(x);
            }
        }
        System.out.println();

        // the piece selected by the player
        selectedPiece = input.nextInt();

        System.out.println();

        System.out.println("Please select one from the available pieces in a piece:");
        // find available pieces of a piece
        ArrayList<Integer> availablePiecesInPiece = availablePiecesInPiece(board, selectedPiece, playerName);

        for (int x = 0; x < availablePiecesInPiece.size(); x++) {
            printAvailablePiecesInPiece(x, availablePiecesInPiece.get(x), allPieces[selectedPiece]);
        }
        System.out.println();

        // which piece the player has selected?
        selectedPieceNumber = input.nextInt();

        System.out.println();

        System.out.println("Please see available " + allPieces[selectedPiece] + " directions:");

        // find available direction of that piece and display for pawn
        if (allPieces[selectedPiece] == Pieces.PAWN.label) {

            // find direction and steps for pawn
            findDirectionAndSteps = Pawn.findDirectionAndStepsForPawn(board, allPieces[selectedPiece], playerName,
                    selectedPieceNumber);

            System.out.println("Available special moves:");
            if (enPassantRight) {
                System.out.print("19 : enPassantRight  ");
            }
            if (enPassantLeft) {
                System.out.print("20 : enPassantLeft  ");
            }
            if (piecePromotionStraight) {
                System.out.print("21 : piecePromotion  ");
            }
            if (piecePromotionLeft) {
                System.out.print("22 : piecePromotionLeft  ");
            }
            if (piecePromotionRight) {
                System.out.print("23 : piecePromotionRight  ");
            }
            // print the directions and steps for pawn
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
            enPassantLeft = false;
            enPassantRight = false;
            piecePromotionStraight = false;
            piecePromotionLeft = false;
            piecePromotionRight = false;
        }

        else if (allPieces[selectedPiece] == Pieces.KNIGHT.label) {

            Boolean enemy = null;

            if (playerName == 0) {
                enemy = false;
            } else {
                enemy = true;
            }

            // find direction and steps for knight
            findDirectionAndSteps = Knight.findDirectionAndStepsForKnight(board, allPieces[selectedPiece],
                    playerName,
                    selectedPieceNumber, enemy);

            // print the directions and steps for knight
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
        } else if (allPieces[selectedPiece] == Pieces.BISHOP.label) {

            Boolean enemy = null;

            if (playerName == 0) {
                enemy = false;
            } else {
                enemy = true;
            }

            // find direction and steps for Bishop
            findDirectionAndSteps = Bishop.findDirectionAndStepsForBishop(board, allPieces[selectedPiece],
                    playerName,
                    selectedPieceNumber, enemy);

            // print the directions and steps for Bishop
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
        } else if (allPieces[selectedPiece] == Pieces.QUEEN.label) {

            Boolean enemy = null;

            if (playerName == 0) {
                enemy = false;
            } else {
                enemy = true;
            }

            // find direction and steps for knight
            findDirectionAndSteps = Queen.findDirectionAndStepsForQueen(board, allPieces[selectedPiece],
                    playerName,
                    selectedPieceNumber, enemy);

            // print the directions and steps for knight
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
        } else if (allPieces[selectedPiece] == Pieces.ROOK.label) {

            Boolean enemy = null;

            if (playerName == 0) {
                enemy = false;
            } else {
                enemy = true;
            }

            // find direction and steps for knight
            findDirectionAndSteps = Rook.findDirectionAndStepsForRook(board, allPieces[selectedPiece],
                    playerName,
                    selectedPieceNumber, enemy);

            // print the directions and steps for knight
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
        } else if (allPieces[selectedPiece] == Pieces.KING.label) {

            Boolean enemy = null;

            if (playerName == 0) {
                enemy = false;
            } else {
                enemy = true;
            }

            // find direction and steps for king
            findDirectionAndSteps = King.findDirectionAndStepsForKing(board, allPieces[selectedPiece],
                    playerName,
                    selectedPieceNumber, enemy);

            System.out.println("Available special moves:");
            if (castleRight) {
                System.out.print("17 : Castle Right");
                System.out.print("   ");
            }
            if (castleLeft) {
                System.out.print("18 : Castle Left");
                System.out.print("   ");
            }
            System.out.println("\nAvailable moves:");
            // print the directions and steps for king
            for (int x = 1; x < findDirectionAndSteps.size(); x++) {
                printAvailablePieceDirections(x, findDirectionAndSteps.get(x));
            }
            // reset castle
            castleRight = false;
            castleLeft = false;
        }
        System.out.println();

        System.out.println("Please select available " + allPieces[selectedPiece] + " directions:");

        direction = input.nextInt();

        System.out.println();

        System.out.println("Please select available steps from available total for the piece:");

        // castling
        if (direction == 17 || direction == 18) {
            System.out.println("Performing castling.");
            steps = 0;
        } else if (direction == 19 || direction == 20) {
            System.out.println("Performing enpassant.");
            steps = 0;
        } else if (direction == 21 || direction == 22 || direction == 23) {
            System.out.println("Performing piece promotion.");
            steps = 0;
        } else {
            if (allPieces[selectedPiece] == Pieces.PAWN.label) {
                // find available total steps of that piece and display
                System.out
                        .print("Total available steps in the selected direction : " + findDirectionAndSteps.get(0));
            } else {
                // find available total steps of that piece and display
                System.out
                        .print("Total available steps in the selected direction : "
                                + findDirectionAndSteps.get(direction));
            }
            System.out.println();
            steps = input.nextInt();
            System.out.println();
        }
        return Arrays.asList(selectedPiece, selectedPieceNumber, steps, direction);
    }

    private static void printAvailablePieceDirections(int whichDirection, int stepsAvailable) {
        if (whichDirection == 1 && stepsAvailable >= 1) {
            System.out.print("1 : Top");
            System.out.print("   ");
        }
        if (whichDirection == 2 && stepsAvailable >= 1) {
            System.out.print("2 : diagonalLeftBottom");
            System.out.print("   ");
        }
        //
        if (whichDirection == 3 && stepsAvailable >= 1) {
            System.out.print("3 : diagonalRightBottom");
            System.out.print("   ");
        }
        if (whichDirection == 4 && stepsAvailable >= 1) {
            System.out.print("4 : diagonalLeftTop");
            System.out.print("   ");
        }
        if (whichDirection == 5 && stepsAvailable >= 1) {
            System.out.print("5 : diagonalRightTop");
            System.out.print("   ");
        }
        if (whichDirection == 6 && stepsAvailable >= 1) {
            System.out.print("6 : topRight1");
            System.out.print("   ");
        }
        if (whichDirection == 7 && stepsAvailable >= 1) {
            System.out.print("7 : topRight2");
            System.out.print("   ");
        }
        if (whichDirection == 8 && stepsAvailable >= 1) {
            System.out.print("8 : rightDown1");
            System.out.print("   ");
        }
        if (whichDirection == 9 && stepsAvailable >= 1) {
            System.out.print("9 : rightDown2");
            System.out.print("   ");
        }
        if (whichDirection == 10 && stepsAvailable >= 1) {
            System.out.print("10 : downRight1");
            System.out.print("   ");
        }
        if (whichDirection == 11 && stepsAvailable >= 1) {
            System.out.print("11 : downRight2");
            System.out.print("   ");
        }
        if (whichDirection == 12 && stepsAvailable >= 1) {
            System.out.print("12 : leftRight1");
            System.out.print("   ");
        }
        if (whichDirection == 13 && stepsAvailable >= 1) {
            System.out.print("13 : leftRight2");
            System.out.print("   ");
        }
        if (whichDirection == 14 && stepsAvailable >= 1) {
            System.out.print("14 : bottom");
            System.out.print("   ");
        }
        if (whichDirection == 15 && stepsAvailable >= 1) {
            System.out.print("15 : left");
            System.out.print("   ");
        }
        if (whichDirection == 16 && stepsAvailable >= 1) {
            System.out.print("16 : right");
            System.out.print("   ");
        }
    }

    /**
     * performs real moves
     * 
     * @param board
     * @param fromPiece
     * @param toPiece
     * @param direction
     * @param integer
     * @return
     */
    public static Piece[][] doMoves(Piece[][] board, Piece fromPiece, Piece toPiece, String direction,
            Integer integer) {
        int fromX = fromPiece.positionX;
        int fromY = fromPiece.positionY;
        int toX = toPiece.positionX;
        int toY = toPiece.positionY;

        // FROM WHERE THE PIECE IS MOVING

        if (toPiece.label != Pieces.BLANK.label) {
            board[fromX][fromY] = new Piece(null, toX, toY, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
        } else {
            board[fromX][fromY] = toPiece;
        }

        if (board[fromX][fromY].label != Pieces.BLANK.label && board[fromX][fromY].moved == false) {
            board[fromX][fromY].moved = true;
        }

        // TO WHERE THE PIECE IS MOVING

        board[toX][toY] = fromPiece;
        if (board[toX][toY].label != Pieces.BLANK.label && board[toX][toY].moved == false) {
            board[toX][toY].moved = true;
        }

        board[fromX][fromY].positionX = fromX;
        board[fromX][fromY].positionY = fromY;

        board[toX][toY].positionX = toX;
        board[toX][toY].positionY = toY;

        return board;
    }

    /**
     * returns direction name
     * 
     * @param direction
     * @return
     */
    public static String selectedDirection(int direction) {

        String whichDirection = "";

        if (direction == 1) {
            return Moves.TOP.label;
        }
        if (direction == 2) {
            whichDirection = Moves.DIAGONAL_LEFT_BOTTOM.label;
        }
        if (direction == 3) {
            whichDirection = Moves.DIAGONAL_RIGHT_BOTTOM.label;
        }
        if (direction == 4) {
            whichDirection = Moves.DIAGONAL_LEFT_TOP.label;
        }
        if (direction == 5) {
            whichDirection = Moves.DIAGONAL_RIGHT_TOP.label;
        }
        if (direction == 6) {
            whichDirection = Moves.TOP_RIGHT_1.label;
        }
        if (direction == 7) {
            whichDirection = Moves.TOP_RIGHT_2.label;
        }
        if (direction == 8) {
            whichDirection = Moves.RIGHT_DOWN_1.label;
        }
        if (direction == 9) {
            whichDirection = Moves.RIGHT_DOWN_2.label;
        }
        if (direction == 10) {
            whichDirection = Moves.DOWN_RIGHT_1.label;
        }
        if (direction == 11) {
            whichDirection = Moves.DOWN_RIGHT_2.label;
        }
        if (direction == 12) {
            whichDirection = Moves.LEFT_RIGHT_1.label;
        }
        if (direction == 13) {
            whichDirection = Moves.LEFT_RIGHT_2.label;
        }
        if (direction == 14) {
            whichDirection = Moves.BOTTOM.label;
        }
        if (direction == 15) {
            whichDirection = Moves.LEFT.label;
        }
        if (direction == 16) {
            whichDirection = Moves.RIGHT.label;
        }
        if (direction == 17) {
            whichDirection = Moves.RIGHT_CASTLE.label;
        }
        if (direction == 18) {
            whichDirection = Moves.LEFT_CASTLE.label;
        }
        return whichDirection;
    }

    /**
     * universal find moving piece for all pieces available in the board
     * 
     * @param board
     * @param selectedPieceNumber
     * @param pieceLabel
     * @return
     */
    public static Piece findFrom(Piece[][] board, int selectedPieceNumber, String pieceLabel) {
        Piece p = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].label != Pieces.BLANK.label) {
                    if (board[i][j].number == selectedPieceNumber && board[i][j].label == pieceLabel) {
                        p = board[i][j];
                    }
                }
            }
        }
        return p;
    }

    /**
     * find the place to where the desired piece will be moved
     * 
     * @param board
     * @param pieceLabel
     * @param x
     * @param steps
     * @param y
     * @param playerName
     * @param direction
     * @return
     */
    public static Piece findTo(Piece[][] board, String pieceLabel, int x, int steps, int y, int playerName,
            String direction) {
        Piece p = null;
        // for pawn only
        if (pieceLabel == Pieces.PAWN.label) {
            if (direction == Moves.TOP.label) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (i == x + steps && j == y && playerName == 0) {
                            p = board[i][j];
                        }
                        if (i == x - steps && j == y && playerName != 0) {
                            p = board[i][j];
                        }
                    }
                }
            } else if (direction == Moves.DIAGONAL_LEFT_BOTTOM.label
                    || direction == Moves.DIAGONAL_RIGHT_BOTTOM.label) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (i == x + steps && j == y - steps && playerName == 0
                                && direction == Moves.DIAGONAL_LEFT_BOTTOM.label) {
                            p = board[i][j];
                        }
                        if (i == x + steps && j == y + steps && playerName == 0
                                && direction == Moves.DIAGONAL_RIGHT_BOTTOM.label) {
                            p = board[i][j];
                        }
                    }
                }
            } else if (direction == Moves.DIAGONAL_LEFT_TOP.label || direction == Moves.DIAGONAL_RIGHT_TOP.label) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (i == x - steps && j == y + steps && playerName != 0
                                && direction == Moves.DIAGONAL_RIGHT_TOP.label) {
                            p = board[i][j];
                        }
                        if (i == x - steps && j == y - steps && playerName != 0
                                && direction == Moves.DIAGONAL_LEFT_TOP.label) {
                            p = board[i][j];
                        }
                    }
                }
            }
        } else if (pieceLabel == Pieces.KNIGHT.label) {

            if (direction == Moves.TOP_RIGHT_1.label) {
                p = board[x - 2][y + 1];
            }

            if (direction == Moves.TOP_RIGHT_2.label) {
                p = board[x - 1][y + 2];
            }

            if (direction == Moves.RIGHT_DOWN_1.label) {
                p = board[x + 1][y + 2];
            }

            if (direction == Moves.RIGHT_DOWN_2.label) {
                p = board[x + 2][y + 1];
            }

            if (direction == Moves.DOWN_RIGHT_1.label) {
                p = board[x + 2][y - 1];
            }

            if (direction == Moves.DOWN_RIGHT_2.label) {
                p = board[x + 1][y - 2];
            }

            if (direction == Moves.LEFT_RIGHT_1.label) {
                p = board[x - 1][y - 2];
            }

            if (direction == Moves.LEFT_RIGHT_2.label) {
                p = board[x - 2][y - 1];
            }
        } else {
            if (direction == Moves.DIAGONAL_LEFT_BOTTOM.label) {
                p = board[x + steps][y - steps];
            }
            if (direction == Moves.DIAGONAL_RIGHT_BOTTOM.label) {
                p = board[x + steps][y + steps];
            }
            if (direction == Moves.DIAGONAL_LEFT_TOP.label) {
                p = board[x - steps][y - steps];
            }
            if (direction == Moves.DIAGONAL_RIGHT_TOP.label) {
                p = board[x - steps][y + steps];
            }
            if (direction == Moves.TOP.label) {
                p = board[x - steps][y];
            }
            if (direction == Moves.BOTTOM.label) {
                p = board[x + steps][y];
            }
            if (direction == Moves.LEFT.label) {
                p = board[x][y - steps];
            }
            if (direction == Moves.RIGHT.label) {
                p = board[x][y + steps];
            }
        }
        return p;
    }

    /**
     * prints all available pieces in a piece
     * 
     * @param x
     * @param number
     * @param selectedPiece
     */
    private static void printAvailablePiecesInPiece(int x, int number, String selectedPiece) {
        System.out.print(number + " : " + selectedPiece + number);
        System.out.print("   ");
    }

    /**
     * prints all available piece
     * 
     * @param x
     */
    public static void printAvailablePieces(int x) {
        System.out.print(x + " : " + allPieces[x]);
        System.out.print("   ");
    }

    /**
     * finds available pieces of a piece
     * 
     * @param board
     * @param selectedPieceNumber
     * @param playerName
     * @return
     */
    public static ArrayList<Integer> availablePiecesInPiece(Piece[][] board, int selectedPieceNumber, int playerName) {
        ArrayList<Integer> availablePiecesInPiece = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (playerName == 0) {
                    if (board[i][j].label != "-") {
                        if (board[i][j].enemy != true
                                && board[i][j].label == allPieces[selectedPieceNumber]) {
                            availablePiecesInPiece.add(board[i][j].number);
                        }
                    }
                } else if (playerName != 0) {
                    if (board[i][j].label != "-") {
                        if (board[i][j].enemy == true
                                && board[i][j].label == allPieces[selectedPieceNumber]) {
                            availablePiecesInPiece.add(board[i][j].number);
                        }
                    }
                }
            }
        }

        return availablePiecesInPiece;
    }

    /**
     * copies a board
     * 
     * @param board
     * @return
     */
    public static Piece[][] copyBoard(Piece[][] board) {
        Piece[][] cb = new Piece[board.length][board.length];
        for (int y = 0; y < board.length; y++) {
            for (int z = 0; z < board.length; z++) {
                cb[y][z] = board[y][z];
            }
        }
        return cb;
    }

    /**
     * finds all available pieces in a board for a player
     * 
     * @param board
     * @param playerName
     * @return
     */
    public static List<Integer> availablePieces(Piece[][] board, int playerName) {

        int pawn = 0;
        int rook = 0;
        int king = 0;
        int queen = 0;
        int bishop = 0;
        int knight = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (playerName == 0) {
                    if (board[i][j].label != "-") {
                        if (board[i][j].enemy != true) {
                            if (board[i][j].label == Pieces.PAWN.label) {
                                pawn = 1;
                            }
                            if (board[i][j].label == Pieces.ROOK.label) {
                                rook = 2;
                            }
                            if (board[i][j].label == Pieces.KING.label) {
                                king = 3;
                            }
                            if (board[i][j].label == Pieces.QUEEN.label) {
                                queen = 4;
                            }
                            if (board[i][j].label == Pieces.BISHOP.label) {
                                bishop = 5;
                            }
                            if (board[i][j].label == Pieces.KNIGHT.label) {
                                knight = 6;
                            }
                        }
                    }
                } else if (playerName != 0) {
                    if (board[i][j].label != "-") {
                        if (board[i][j].enemy == true) {
                            if (board[i][j].label == Pieces.PAWN.label) {
                                pawn = 1;
                            }
                            if (board[i][j].label == Pieces.ROOK.label) {
                                rook = 2;
                            }
                            if (board[i][j].label == Pieces.KING.label) {
                                king = 3;
                            }
                            if (board[i][j].label == Pieces.QUEEN.label) {
                                queen = 4;
                            }
                            if (board[i][j].label == Pieces.BISHOP.label) {
                                bishop = 5;
                            }
                            if (board[i][j].label == Pieces.KNIGHT.label) {
                                knight = 6;
                            }
                        }
                    }
                }
            }
        }
        return Arrays.asList(king, queen, bishop, knight, rook, pawn);
    }

    public static boolean checkKingCanBeSaved(Piece[][] board, int playerName) {
        // find available pieces
        List<Integer> availablePieces = availablePieces(board, playerName);
        for (int x = 1; x < availablePieces.size(); x++) {
            if (availablePieces.get(x) != 0) {
                ArrayList<Integer> availablePiecesInPiece = availablePiecesInPiece(board, x, playerName);
                return true;
            }
        }
        return false;
    }

    public static void printAdd(Piece board, int playerName) {
        if (board.label != Pieces.BLANK.label) {
            System.out.print(ConsoleColors.BLUE + "x " + ConsoleColors.RESET);
        } else {
            System.out.print(ConsoleColors.YELLOW + "x " + ConsoleColors.RESET);
        }

        // add to available list
        if (playerName == 0 || playerName == 2) {
            Utils.allAvailablePiecesSteps.add(board);
        }
    }

    /**
     * finds if any valid moves available for any direction
     * 
     * @param piece
     * @param playerName
     * @param enemy
     * @return
     */
    public static int[] stepsCount(Piece piece, int playerName, boolean enemy) {
        int counts[] = new int[2];
        if (piece.label == Pieces.BLANK.label) {
            counts[1] = 1;
            // add to available list
            if (playerName == 0 || playerName == 2) {
                Utils.allAvailablePiecesSteps.add(piece);
            }
        } else if (piece.enemy == enemy && piece.label != Pieces.BLANK.label) {
            counts[0] = 0;
            return counts;
        } else if (piece.label != Pieces.BLANK.label && piece.enemy != enemy) {
            // add to available list
            if (playerName == 0 || playerName == 2) {
                Utils.allAvailablePiecesSteps.add(piece);
            }
            counts[0] = 0;
            counts[1] = 1;
            return counts;
        }
        counts[0] = 1;
        return counts;
    }
}