import java.util.Scanner;

public class App {

    // used for input
    static Scanner input = new Scanner(System.in);
    // new board object
    static Board board = new Board();
    static int playerInput;
    static int selectedPiece;
    static int selectedPieceNumber;
    static int steps;

    public static void main(String[] args) throws Exception {

        // create any sort of board
        System.out.println("Welcome to the Chess Game!");
        System.out.print("Please, enter the AI ply depth: ");
        Minimax.finalPly = input.nextInt();
        System.out.println("Do you want to build your own board? \n1: Yes 0: No");
        playerInput = input.nextInt();
        if (playerInput == 0)
            // make a default board
            board.buildBoard();
        else {
            // make a board manually
            board.buildManualBoard();
        }
        // print board
        board.printBoard();

        System.out.println("\nPlease choose whether do you want to play with human or computer");

        System.out.print("0 : Human Vs. Human");
        System.out.print("   ");

        System.out.print("1 : Human Vs. Computer");
        System.out.print("   ");

        System.out.print("Any Other Number : Exit");
        System.out.println();
        // menu
        playerInput = input.nextInt();
        System.out.println();

        while (true) {
            // HUMAN VS HUMAN
            if (playerInput == 0) {
                // check if the king is safe to move or else its checkmate and until user makes
                // a move that save king
                if (King.checkKingSafe(board.board, 0)) {
                    if (Utils.checkKingCanBeSaved(board.board, 0)) {
                        // checkmate
                        while (King.checkKingSafe(board.board, 0)) {
                            System.out.println(
                                    "Player-" + 0 + " its checkmate, please make a legal move to save your king.");
                            Player.moves(board, 0);
                        }
                    } else {
                        System.out.println("Player-" + 1 + " has won the game.");
                    }
                } else {
                    Player.moves(board, 0);
                }
                if (King.checkKingSafe(board.board, 1)) {
                    if (Utils.checkKingCanBeSaved(board.board, 1)) {
                        while (King.checkKingSafe(board.board, 1)) {
                            System.out.println(
                                    "Player-" + 1 + " its checkmate, please make a legal move to save your king.");
                            Player.moves(board, 1);
                        }
                    } else {
                        System.out.println("Player-" + 0 + " has won the game.");
                    }
                } else {
                    Player.moves(board, 1);
                }
            }
            // AI VS HUMAN
            else if (playerInput == 1) {
                // check if the king is safe to move or else its checkmate and until user makes
                // a move that save king
                if (King.checkKingSafe(board.board, 0)) {
                    if (Utils.checkKingCanBeSaved(board.board, 0)) {
                        // checkmate
                        while (King.checkKingSafe(board.board, 0)) {
                            System.out.println(
                                    "Player-" + 0 + " its checkmate, please make a legal move to save your king.");
                            Player.moves(board, 0);
                        }
                    } else {
                        System.out.println("AI has won the game.");
                    }
                } else {
                    Player.moves(board, 0);
                }
                // AI moves
                if (King.checkKingSafe(board.board, 2)) {
                    if (Utils.checkKingCanBeSaved(board.board, 2)) {// checkmate
                        // check
                        while (King.checkKingSafe(board.board, 2)) {
                            board.board = Minimax.moves(board.board, 2);
                        }
                    } else {
                        System.out.println("Player-" + 0 + " has won the game.");
                    }
                } else {
                    board.board = Minimax.moves(board.board, 2);
                }
                // print board
                board.printBoard();
            } else {
                break;
            }
        }
    }
}
