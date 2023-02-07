import java.util.Scanner;

public class Board {

    Piece[][] board = new Piece[Length.LENGTH.length][Length.LENGTH.length];
    Piece[][] manualBoard = new Piece[Length.LENGTH.length][Length.LENGTH.length];
    Piece piece;
    int n1, n2, n3, n4, n5, n6;
    static Scanner input = new Scanner(System.in);

    /**
     * builds a default chess board
     */
    public void buildBoard() {

        System.out.println("Building Chess Board...");
        Boolean enemy = null;

        for (int i = 0; i < board.length; i++) {

            if (i == 0 || i == 1)
                enemy = false;
            else if (i == 6 || i == 7)
                enemy = true;
            else
                enemy = null;

            for (int j = 0; j < board.length; j++) {

                if ((i == 0 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 0) || (i == 7 && j == 7)) {

                    piece = new Piece(false, i, j, Pieces.ROOK.label, false, Power.ROOK.power, enemy, n1);
                    board[i][j] = piece;
                    n1++;

                } else if ((i == 0 && j == 1) || (i == 0 && j == 6) || (i == 7 && j == 1) || (i == 7 && j == 6)) {

                    piece = new Piece(false, i, j, Pieces.KNIGHT.label, false, Power.KNIGHT.power, enemy, n2);
                    board[i][j] = piece;
                    n2++;

                } else if ((i == 0 && j == 2) || (i == 0 && j == 5) || (i == 7 && j == 2) || (i == 7 && j == 5)) {

                    piece = new Piece(false, i, j, Pieces.BISHOP.label, false, Power.BISHOP.power, enemy, n3);
                    board[i][j] = piece;
                    n3++;

                } else if ((i == 0 && j == 4) || (i == 7 && j == 4)) {

                    piece = new Piece(false, i, j, Pieces.KING.label, false, Power.KING.power, enemy, n4);
                    board[i][j] = piece;
                    n4++;

                } else if ((i == 0 && j == 3) || (i == 7 && j == 3)) {

                    piece = new Piece(false, i, j, Pieces.QUEEN.label, false, Power.QUEEN.power, enemy, n5);
                    board[i][j] = piece;
                    n5++;

                } else if ((i == 1) || (i == 6)) {

                    piece = new Piece(false, i, j, Pieces.PAWN.label, false, Power.PAWN.power, enemy, n6);
                    board[i][j] = piece;
                    n6++;

                } else {
                    int number = 0;
                    piece = new Piece(null, i, j, Pieces.BLANK.label, null, Power.BLANK.power, enemy, number);
                    board[i][j] = piece;
                }
            }
        }
    }

    /**
     * Builds a board asking user to place each pieces
     */
    public void buildManualBoard() {
        // build the original board first
        buildBoard();
        System.out.println("\nA typical chess board:");
        printBoard();

        // making blank places first
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                piece = new Piece(null, i, j, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);
                manualBoard[i][j] = piece;
            }
        }

        // placing default king pieces as must
        manualBoard[0][4] = board[0][4];
        manualBoard[7][4] = board[7][4];
        System.out.println(
                "\nNote: A chess board must have atleast both player's king pieces. Pieces will be asked to be entered for both players. Selecting exit (asked for both players) will quit adding anymore pieces and start the game.");
        System.out.println("\nFirstly, make your board. Exit to quit and build oponent's board.");
        // making own board
        makeManualBoardPieces(0, 2, "your");
        System.out.println("\nPlease, make enemy's board. Exit to start the game.");
        // making oppponent's board part
        makeManualBoardPieces(6, board.length, "enemy's");
        System.out.println("Here, is the board built by you!");
        // copying the board as original board
        board = Utils.copyBoard(manualBoard);
    }

    /**
     * making a board by a player himself
     * 
     * @param min
     * @param max
     * @param player
     * @return
     */
    private int makeManualBoardPieces(int min, int max, String player) {
        int x, y, agree;
        for (int i = min; i < max; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].label != Pieces.KING.label) {
                    System.out
                            .println("\nDo you want to place " + player + " " + board[i][j].label + board[i][j].number
                                    + "?");
                    System.out.println("1: Yes  2: No  3: Exit");
                    // stop adding anymore pieces
                    agree = input.nextInt();
                    if (agree == 1) {
                        System.out.println("Where do you want to place your " + board[i][j].label + "?");
                        System.out.println("Please input position X (must be 0-7):");
                        x = input.nextInt();
                        System.out.println("Please input position Y (must be 0-7):");
                        y = input.nextInt();
                        board[i][j].positionX = x;
                        board[i][j].positionY = y;
                        manualBoard[x][y] = board[i][j];
                    } else if (agree == 3) {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * print the board
     */
    public void printBoard() {
        int x = 0;
        int y = 0;
        while (x < board.length) {
            while (y < board.length) {
                //
                if (board[x][y].enemy != null && board[x][y].enemy == false) {
                    if (board[x][y].number >= 10) {
                        System.out.print(ConsoleColors.GREEN + board[x][y].label + board[x][y].number + "  "
                                + ConsoleColors.RESET);
                    } else {
                        System.out.print(ConsoleColors.GREEN + board[x][y].label + board[x][y].number + "   "
                                + ConsoleColors.RESET);
                    }
                } else if (board[x][y].enemy != null && board[x][y].enemy == true) {
                    if (board[x][y].number >= 10) {
                        System.out.print(ConsoleColors.RED + board[x][y].label + board[x][y].number + "  "
                                + ConsoleColors.RESET);
                    } else {
                        System.out.print(ConsoleColors.RED + board[x][y].label + board[x][y].number + "   "
                                + ConsoleColors.RESET);
                    }
                } else {
                    System.out.print(board[x][y].label + "    ");
                }
                y++;
            }
            System.out.println();
            x++;
            y = 0;
        }
        System.out.println();
    }
}
