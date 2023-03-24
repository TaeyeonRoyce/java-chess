package chess.domain.game.state.finished;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.position.ChessBoard;

public class EndGame extends FinishedGame {

    public EndGame(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public GameResult calculateResult() {
        return new GameResult(MatchResult.DRAW);
    }
}