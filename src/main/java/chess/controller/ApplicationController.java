package chess.controller;

import chess.controller.game.ChessGameController;
import chess.controller.game.ControllerMapper;
import chess.controller.request.GameRequest;
import chess.controller.request.RequestCommandHandler;
import chess.domain.game.ChessCommandType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Set;

public class ApplicationController {

    private static final Set<String> runningConditionCommand = Set.of(
            "INIT",
            "START",
            "FETCH",
            "MOVE"
    );

    public void run() {
        String gameState = "INIT";
        OutputView.printStartPrefix();
        while (runningConditionCommand.contains(gameState)) {
            ChessCommandType commandType = retryOnInvalidState();
            gameState = commandType.name();
        }
    }

    private ChessCommandType retryOnInvalidState() {
        try {
            List<String> requests = InputView.readCommands();

            GameRequest gameRequest = RequestCommandHandler.handleRequest(requests);
            ChessCommandType commandType = gameRequest.getCommandType();
            ChessGameController controller = ControllerMapper.getController(commandType);

            controller.service(gameRequest);
            return commandType;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return retryOnInvalidState();
        }
    }
}
