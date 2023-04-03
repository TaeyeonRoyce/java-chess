package chess.controller.game;

import chess.controller.request.GameRequest;
import chess.controller.request.position.PositionMapper;
import chess.domain.game.state.ChessGame;
import chess.domain.position.Position;
import chess.domain.service.LoadGameService;
import chess.domain.service.MoveService;
import chess.view.OutputView;
import chess.view.output.board.ChessBoardStateFormatter;
import java.util.List;

public class MoveController implements ChessGameController {
    private final MoveService moveService;
    private final LoadGameService loadGameService;

    public MoveController(MoveService moveService, LoadGameService loadGameService) {
        this.moveService = moveService;
        this.loadGameService = loadGameService;
    }

    @Override
    public void service(GameRequest gameRequest) {
        ChessGame chessGame = loadGameService.loadExistGame();

        String from = gameRequest.getParamValue("from");
        String to = gameRequest.getParamValue("to");
        Position fromPosition = PositionMapper.toPosition(from);
        Position toPosition = PositionMapper.toPosition(to);

        moveService.move(chessGame, fromPosition, toPosition);

        printChessStatus(chessGame);
    }

    private void printChessStatus(ChessGame chessGame) {
        List<List<String>> consoleViewBoard = ChessBoardStateFormatter.convertToConsoleViewBoard(
                chessGame.getPiecesPosition());

        OutputView.printChessState(consoleViewBoard);
    }
}
