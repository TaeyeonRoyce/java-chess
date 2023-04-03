package chess.controller.request;

import chess.domain.game.ChessCommandType;
import java.util.List;
import java.util.Map;

public enum RequestCommandHandler {
;
    private static final int SIMPLE_COMMAND_LENGTH = 1;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;

    public static GameRequest handleRequest(List<String> request) {
        ChessCommandType commandType = ChessCommandTypeMapper.toChessCommandType(request.get(COMMAND_INDEX));
        if (request.size() == SIMPLE_COMMAND_LENGTH) {
            return new GameRequest(commandType);
        }

        if (request.size() == MOVE_COMMAND_LENGTH && commandType == ChessCommandType.MOVE) {
            return new GameRequest(commandType, parseMoveRequest(request));
        }

        throw new IllegalArgumentException("옳바른 명령을 입력하세요.");
    }

    private static Map<String, String> parseMoveRequest(List<String> request) {
        return Map.of(
                "from", request.get(FROM_INDEX),
                "to", request.get(TO_INDEX)
        );
    }
}
