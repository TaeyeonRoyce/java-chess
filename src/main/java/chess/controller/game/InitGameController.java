package chess.controller.game;

import chess.controller.request.GameRequest;
import chess.domain.game.state.ChessGame;
import chess.domain.service.InitGameService;
import chess.domain.service.LoadGameService;
import chess.view.OutputView;
import chess.view.output.board.ChessBoardStateFormatter;
import java.util.List;

public class InitGameController implements ChessGameController {

    private final InitGameService initGameService;
    private final LoadGameService loadGameService;

    public InitGameController(InitGameService initGameService, LoadGameService loadGameService) {
        this.initGameService = initGameService;
        this.loadGameService = loadGameService;
    }

    @Override
    public void service(GameRequest ignore) {
        initGameService.initGame();
        ChessGame chessGame = loadGameService.loadExistGame();

        printChessStatus(chessGame);
    }

    private void printChessStatus(ChessGame chessGame) {
        List<List<String>> consoleViewBoard = ChessBoardStateFormatter.convertToConsoleViewBoard(
                chessGame.getPiecesPosition());

        OutputView.printChessState(consoleViewBoard);
    }


}
