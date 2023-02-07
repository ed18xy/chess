import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AI {
    // first names of all pieces
    public static String[] allPieces = { "K", "Q", "B", "N", "R", "P" };
    // all the moves for user board
    public static ArrayList<Piece[][]> allMovedBoard = new ArrayList<>();
    // all the moves for AI board
    public static ArrayList<Piece[][]> allMovedBoardAI = new ArrayList<>();

    // finds all the moves for AI and a USER
    public static void moves(Piece[][] board, int playerName) throws CloneNotSupportedException {
        // clear any previous moves
        allMovedBoard.clear();
        allMovedBoardAI.clear();

        // availables pieces of a player
        List<Integer> availablePieces = Utils.availablePieces(board, playerName);

        // copy board to find new moves
        Piece[][] copiedBoard = new Piece[board.length][board.length];
        copiedBoard = Utils.copyBoard(board);

        // DO NOT SHOW THE PRINTS
        PrintStream originalStream = System.out;
        PrintStream dummyStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                // NO-OP
            }
        });
        System.setOut(dummyStream);
        // DO NOT SHOW THE PRINTS

        // for AI TOTAL MOVED LOCATIONS
        for (int x = 0; x < availablePieces.size(); x++) {
            if (availablePieces.get(x) != 0) {
                if (allPieces[x] == Pieces.PAWN.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Pawn.findDirectionAndStepsForPawn(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y));
                    }
                } else if (allPieces[x] == Pieces.ROOK.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Rook.findDirectionAndStepsForRook(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y), true);
                    }
                } else if (allPieces[x] == Pieces.KNIGHT.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Knight.findDirectionAndStepsForKnight(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y), true);
                    }
                } else if (allPieces[x] == Pieces.BISHOP.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Bishop.findDirectionAndStepsForBishop(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y), true);
                    }
                } else if (allPieces[x] == Pieces.QUEEN.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Queen.findDirectionAndStepsForQueen(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y), true);
                    }
                } else if (allPieces[x] == Pieces.KING.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            playerName);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        King.findDirectionAndStepsForKing(board, allPieces[x], playerName,
                                availablePiecesInPiece.get(y), true);
                    }
                }
            }
        }

        // FOR USER Total MOVED LOCATIONS
        for (int x = 0; x < availablePieces.size(); x++) {
            if (availablePieces.get(x) != 0) {
                if (allPieces[x] == Pieces.PAWN.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Pawn.findDirectionAndStepsForPawn(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y));
                    }
                } else if (allPieces[x] == Pieces.ROOK.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Rook.findDirectionAndStepsForRook(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y), false);
                    }
                } else if (allPieces[x] == Pieces.KNIGHT.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Knight.findDirectionAndStepsForKnight(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y), false);
                    }
                } else if (allPieces[x] == Pieces.BISHOP.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Bishop.findDirectionAndStepsForBishop(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y), false);
                    }
                } else if (allPieces[x] == Pieces.QUEEN.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        Queen.findDirectionAndStepsForQueen(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y), false);
                    }
                } else if (allPieces[x] == Pieces.KING.label) {
                    ArrayList<Integer> availablePiecesInPiece = Utils.availablePiecesInPiece(board, x,
                            0);

                    for (int y = 0; y < availablePiecesInPiece.size(); y++) {
                        King.findDirectionAndStepsForKing(board, allPieces[x], 0,
                                availablePiecesInPiece.get(y), false);
                    }
                }
            }
        }

        // NOW SHOW THE PRINTS
        System.setOut(originalStream);
        // NOW SHOW THE PRINTS

        // FOR AI
        // moves helper function performs new moves for boards
        for (int m = 0; m < Utils.allAvailableStepsPawnAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsPawnAI.get(0).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard, Utils.allAvailableStepsPawnAI.get(0).get(0),
                        Utils.allAvailableStepsPawnAI.get(0).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        for (int m = 0; m < Utils.allAvailableStepsRookAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsRookAI.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsRookAI.get(m).get(0),
                        Utils.allAvailableStepsRookAI.get(m).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        for (int m = 0; m < Utils.allAvailableStepsKnightAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsKnightAI.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsKnightAI.get(m).get(0),
                        Utils.allAvailableStepsKnightAI.get(m).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);

            }
        }

        for (int m = 0; m < Utils.allAvailableStepsBishopAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsBishopAI.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsBishopAI.get(m).get(0),
                        Utils.allAvailableStepsBishopAI.get(m).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }
        for (int m = 0; m < Utils.allAvailableStepsQueenAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsQueenAI.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsQueenAI.get(m).get(0),
                        Utils.allAvailableStepsQueenAI.get(m).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);

            }
        }
        for (int m = 0; m < Utils.allAvailableStepsKingAI.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsKingAI.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsKingAI.get(m).get(0),
                        Utils.allAvailableStepsKingAI.get(m).get(n));
                allMovedBoardAI.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        // FOR USER
        for (int m = 0; m < Utils.allAvailableStepsPawn.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsPawn.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsPawn.get(m).get(0),
                        Utils.allAvailableStepsPawn.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        for (int m = 0; m < Utils.allAvailableStepsRook.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsRook.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsRook.get(m).get(0),
                        Utils.allAvailableStepsRook.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        for (int m = 0; m < Utils.allAvailableStepsKnight.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsKnight.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsKnight.get(m).get(0),
                        Utils.allAvailableStepsKnight.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        for (int m = 0; m < Utils.allAvailableStepsBishop.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsBishop.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsBishop.get(m).get(0),
                        Utils.allAvailableStepsBishop.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }
        for (int m = 0; m < Utils.allAvailableStepsQueen.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsQueen.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsQueen.get(m).get(0),
                        Utils.allAvailableStepsQueen.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);

            }
        }
        for (int m = 0; m < Utils.allAvailableStepsKing.size(); m++) {
            for (int n = 1; n < Utils.allAvailableStepsKing.get(m).size(); n++) {
                Piece[][] moveHelp = movesHelper(copiedBoard,
                        Utils.allAvailableStepsKing.get(m).get(0),
                        Utils.allAvailableStepsKing.get(m).get(n));
                allMovedBoard.add(moveHelp);
                copiedBoard = Utils.copyBoard(board);
            }
        }

        // clear all the steps list created by alpha-beta
        Utils.allAvailablePiecesSteps.clear();
        Utils.allAvailableStepsPawn.clear();
        Utils.allAvailableStepsBishop.clear();
        Utils.allAvailableStepsKing.clear();
        Utils.allAvailableStepsKnight.clear();
        Utils.allAvailableStepsQueen.clear();
        Utils.allAvailableStepsRook.clear();
        Utils.allAvailableStepsPawnAI.clear();
        Utils.allAvailableStepsBishopAI.clear();
        Utils.allAvailableStepsKingAI.clear();
        Utils.allAvailableStepsKnightAI.clear();
        Utils.allAvailableStepsQueenAI.clear();
        Utils.allAvailableStepsRookAI.clear();
    }

    /**
     * helps to perform moves for AI
     * 
     * @param board
     * @param fromPiece
     * @param toPiece
     * @return
     * @throws CloneNotSupportedException
     */
    public static Piece[][] movesHelper(Piece[][] board, Piece fromPiece, Piece toPiece)
            throws CloneNotSupportedException {
        int fromX = fromPiece.positionX;
        int fromY = fromPiece.positionY;
        int toX = toPiece.positionX;
        int toY = toPiece.positionY;

        // FROM WHERE THE PIECE IS MOVING

        board[fromX][fromY] = new Piece(null, toX, toX, Pieces.BLANK.label, null, Power.BLANK.power, null, 0);

        // TO WHERE THE PIECE IS MOVING
        board[toX][toY] = (Piece) fromPiece.clone();
        board[toX][toY].moved = true;

        board[fromX][fromY].positionX = fromX;
        board[fromX][fromY].positionY = fromY;

        board[toX][toY].positionX = toX;
        board[toX][toY].positionY = toY;
        return board;
    }
}
