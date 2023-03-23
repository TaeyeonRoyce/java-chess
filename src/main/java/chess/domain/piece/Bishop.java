package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;

public final class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Camp camp) {
        super(camp);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        if (isPieceRule(from, to)) {
            return new BlockingMove();
        }

        return new InvalidMove();
    }

    @Override
    protected boolean isPieceRule(Position from, Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return Math.abs(fileGap) == Math.abs(rankGap);
    }

    @Override
    public double appendScore(double source, boolean ignore) {
        return BISHOP_SCORE;
    }
}
