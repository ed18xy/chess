enum Pieces {
    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    PAWN("P"),
    BLANK("-");

    public final String label;

    private Pieces(String label) {
        this.label = label;
    }
}

enum Length {

    LENGTH(8);

    public final int length;

    private Length(int length) {
        this.length = length;
    }
}

enum Moves {

    RIGHT("right"),
    LEFT("left"),
    TOP("top"),
    BOTTOM("bottom"),
    DIAGONAL_RIGHT_TOP("diagonalRightTop"),
    DIAGONAL_LEFT_TOP("diagonalLeftTop"),
    DIAGONAL_RIGHT_BOTTOM("diagonalRightBottom"),
    DIAGONAL_LEFT_BOTTOM("diagonalLeftBottom"),
    TOP_RIGHT_1("topRight1"),
    TOP_RIGHT_2("topRight2"),
    RIGHT_DOWN_1("rightDown1"),
    RIGHT_DOWN_2("rightDown2"),
    DOWN_RIGHT_1("downRight1"),
    DOWN_RIGHT_2("downRight2"),
    LEFT_RIGHT_1("leftRight1"),
    LEFT_RIGHT_2("leftRight2"),
    RIGHT_CASTLE("rightCastle"),
    LEFT_CASTLE("leftCastle"),
    ENPASSANT_RIGHT("enPassantRight"),
    ENPASSANT_LEFT("enPassantLeft"),
    PIECE_PROMOTION_STRAIGHT("piecePromotionStraight"),
    PIECE_PROMOTION_LEFT("piecePromotionLeft"),
    PIECE_PROMOTION_RIGHT("piecePromotionRight");

    public final String label;

    private Moves(String label) {
        this.label = label;
    }
}

enum Power {

    KING(300),
    QUEEN(250),
    BISHOP(200),
    KNIGHT(150),
    ROOK(100),
    PAWN(50),
    BLANK(0);

    public final int power;

    private Power(int power) {
        this.power = power;
    }
}