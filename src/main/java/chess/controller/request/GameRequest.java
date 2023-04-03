package chess.controller.request;

import chess.domain.game.ChessCommandType;
import java.util.Collections;
import java.util.Map;

public class GameRequest {

    private final ChessCommandType commandType;
    private final Map<String, String> requestParams;

    public GameRequest(ChessCommandType commandType) {
        this.commandType = commandType;
        this.requestParams = Collections.emptyMap();
    }

    public GameRequest(ChessCommandType commandType, Map<String, String> requestParams) {
        this.commandType = commandType;
        this.requestParams = requestParams;
    }

    public ChessCommandType getCommandType() {
        return commandType;
    }

    public String getParamValue(String param) {
        return requestParams.get(param);
    }
}
