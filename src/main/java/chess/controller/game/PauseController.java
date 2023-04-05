package chess.controller.game;

import chess.controller.request.GameRequest;
import chess.domain.game.state.ChessGame;
import chess.domain.service.LoadGameService;
import chess.domain.service.PauseService;
import chess.view.OutputView;
import chess.view.output.board.ChessBoardStateFormatter;
import java.util.List;

public class PauseController implements ChessGameController {
    private final PauseService pauseService;
    private final LoadGameService loadGameService;

    public PauseController(PauseService pauseService, LoadGameService loadGameService) {
        this.pauseService = pauseService;
        this.loadGameService = loadGameService;
    }

    @Override
    public void service(GameRequest ignore) {
        pauseService.pauseGame();
        ChessGame chessGame = loadGameService.loadExistGame();
        printChessStatus(chessGame);

        OutputView.printPausedMessage();
    }

    private void printChessStatus(ChessGame chessGame) {
        List<List<String>> consoleViewBoard = ChessBoardStateFormatter.convertToConsoleViewBoard(
                chessGame.getPiecesPosition());

        OutputView.printChessState(consoleViewBoard);
    }
}
