package chess.controller.game;

import chess.DependencyConfigurer;
import chess.domain.game.ChessCommandType;
import java.util.Map;

public enum ControllerMapper {
    ;

    private static final Map<ChessCommandType, ChessGameController> controllerByCommand = Map.of(
            ChessCommandType.START, DependencyConfigurer.getInitGameController(),
            ChessCommandType.FETCH, DependencyConfigurer.getLoadGameController(),
            ChessCommandType.MOVE, DependencyConfigurer.getMoveController(),
            ChessCommandType.STATUS, DependencyConfigurer.getStatusGameController(),
            ChessCommandType.PAUSE, DependencyConfigurer.getPauseController()
    );

    public static ChessGameController getController(ChessCommandType commandType) {
        return controllerByCommand.get(commandType);
    }

}
