package chess.domain.service;

import chess.domain.game.state.ChessGame;
import chess.domain.position.Position;
import chess.repository.BoardDao;
import chess.repository.PieceDao;
import chess.repository.mapper.PositionValueConverter;

public class MoveService {
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public MoveService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public void move(ChessGame chessGame, Position fromPosition, Position toPosition) {
        chessGame.move(fromPosition, toPosition);

        String toValue = PositionValueConverter.convertToValue(toPosition);
        if (pieceDao.isExistByPosition(toValue)) {
            pieceDao.deleteByPosition(toValue);
        }

        String fromValue = PositionValueConverter.convertToValue(fromPosition);
        pieceDao.updatePiecePositionTo(fromValue, toValue);
        boardDao.updateCamp(chessGame.getNextCamp().name());
    }
}
