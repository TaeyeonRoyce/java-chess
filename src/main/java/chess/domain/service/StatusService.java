package chess.domain.service;

import chess.domain.game.result.GameResult;
import chess.domain.game.state.ChessGame;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;

public class StatusService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public StatusService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public GameResult endGameWithResult(ChessGame chessGame) {
        ChessGame status = chessGame.status();
        cleanUpGame();

        return status.calculateResult();
    }

    private void cleanUpGame() {
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }
}
