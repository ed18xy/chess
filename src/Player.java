import java.util.List;

public class Player {

    static Piece fromPiece, toPiece, usePiece;
    static String[] allPieces = Utils.allPieces;

    /**
     * finds directions and steps and performs an actual move
     * 
     * @param board
     * @param playerName
     */
    public static void moves(Board board, int playerName) {

        // getPlayerInputs = selectedPiece, selectedPieceNumber, steps, direction
        List<Integer> getPlayerInputs = Utils.getPlayerInputs(board.board, playerName);

        // direction label
        String direction = Utils.selectedDirection(getPlayerInputs.get(3));
        int directionNumber = getPlayerInputs.get(3);

        // frompiece
        fromPiece = Utils.findFrom(board.board, getPlayerInputs.get(1), allPieces[getPlayerInputs.get(0)]);

        // topiece
        if (directionNumber <= 16) {
            toPiece = Utils.findTo(board.board, fromPiece.label, fromPiece.positionX, getPlayerInputs.get(2),
                    fromPiece.positionY,
                    playerName, direction);
            if (fromPiece != null || toPiece != null || (fromPiece.enemy != toPiece.enemy)) {
                // pawn
                if (allPieces[getPlayerInputs.get(0)] == Pieces.PAWN.label) {
                    board.board = Pawn.pawnMoves(board.board, fromPiece, toPiece, direction, getPlayerInputs.get(2));
                } else {
                    board.board = Utils.doMoves(board.board, fromPiece, toPiece, direction, getPlayerInputs.get(2));
                }
            }
        }
        // perform castling
        else if (directionNumber == 17 || directionNumber == 18) {
            toPiece = King.doCastling(board.board, fromPiece, direction, playerName);
            board.board = King.doCastlingMoves(board.board, fromPiece, toPiece, direction, getPlayerInputs.get(2),
                    playerName);
        }
        // perform enpassant
        else if (directionNumber == 19 || directionNumber == 20) {
            toPiece = Pawn.doEnpassant(board.board, fromPiece, directionNumber, playerName);
            board.board = Pawn.doEnpassantMoves(board.board, fromPiece, toPiece, direction, getPlayerInputs.get(2),
                    playerName);
        }
        // perform piece promotion
        else {
            toPiece = Pawn.doPromote(board.board, fromPiece, directionNumber, playerName);
            board.board = Pawn.doPromoteMoves(board.board, fromPiece, toPiece, direction, getPlayerInputs.get(2),
                    playerName);
        }

        // print current board
        board.printBoard();
    }
}
