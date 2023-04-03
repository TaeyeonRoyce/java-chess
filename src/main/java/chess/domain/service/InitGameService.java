package chess.domain.service;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.Position;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.PieceEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InitGameService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public InitGameService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public void initGame() {
        cleanUpGame();
        saveInitialBoard();
    }

    private void cleanUpGame() {
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }

    private void saveInitialBoard() {
        ChessBoard initialChessBoard = ChessBoardFactory.getInitialChessBoard();
        try {
            Long newBoardId = boardDao.saveBoard(Camp.WHITE.name());
            List<PieceEntity> pieceEntities = getPieceEntities(initialChessBoard, newBoardId);
            pieceDao.savePieces(pieceEntities);
        } catch (RuntimeException e) {
            throw new IllegalStateException();
        }
    }

    private List<PieceEntity> getPieceEntities(ChessBoard chessBoard, Long boardId) {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        Map<Position, Piece> piecesPosition = chessBoard.getPiecesPosition();

        for (Entry<Position, Piece> positionPieceEntry : piecesPosition.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            PieceEntity pieceEntity = PieceEntity.of(position, piece, boardId);
            pieceEntities.add(pieceEntity);
        }

        return pieceEntities;
    }
}
