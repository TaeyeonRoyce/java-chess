package chess.domain.game.state;

import chess.domain.piece.Camp;
import chess.domain.position.ChessBoard;

public class EndGame extends FinishedGame {

    public EndGame(ChessBoard chessBoard, Camp turnCamp) {
        super(chessBoard, turnCamp);
    }
}
