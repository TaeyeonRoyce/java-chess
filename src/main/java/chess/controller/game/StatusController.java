package chess.controller.game;

import chess.controller.request.GameRequest;
import chess.domain.game.result.GameResult;
import chess.domain.game.state.ChessGame;
import chess.domain.service.LoadGameService;
import chess.domain.service.StatusService;
import chess.view.OutputView;
import chess.view.output.result.GameResultFormatter;
import java.util.List;

public class StatusController implements ChessGameController {
    private final StatusService statusService;
    private final LoadGameService loadGameService;

    public StatusController(StatusService statusService, LoadGameService loadGameService) {
        this.statusService = statusService;
        this.loadGameService = loadGameService;
    }

    @Override
    public void service(GameRequest gameRequest) {
        ChessGame chessGame = loadGameService.loadExistGame();
        GameResult gameResult = statusService.endGameWithResult(chessGame);

        List<String> results = GameResultFormatter.convertToGameResult(gameResult);
        OutputView.printResult(results);
    }
}
