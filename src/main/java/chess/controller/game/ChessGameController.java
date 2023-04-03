package chess.controller.game;

import chess.controller.request.GameRequest;

public interface ChessGameController {
    void service(GameRequest gameRequest);
}
