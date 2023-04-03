package chess;

import chess.controller.game.InitGameController;
import chess.controller.game.FetchGameController;
import chess.controller.game.MoveController;
import chess.controller.game.PauseController;
import chess.controller.game.StatusController;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.service.InitGameService;
import chess.domain.service.LoadGameService;
import chess.domain.service.MoveService;
import chess.domain.service.PauseService;
import chess.domain.service.StatusService;

public enum DependencyConfigurer {
;
    private static InitGameController initGameController;
    private static FetchGameController fetchGameController;
    private static MoveController moveController;
    private static PauseController pauseController;
    private static StatusController statusController;
    private static InitGameService initGameService;
    private static LoadGameService loadGameService;
    private static MoveService moveService;
    private static PauseService pauseService;
    private static StatusService statusService;
    private static BoardDao boardDao;
    private static PieceDao pieceDao;

    public static InitGameController getInitGameController() {
        if (initGameController == null) {
            initGameController = new InitGameController(getInitGameService(), getLoadGameservice());
        }

        return initGameController;
    }

    public static MoveController getMoveController() {
        if (moveController == null) {
            moveController = new MoveController(getMoveService(), getLoadGameservice());
        }

        return moveController;
    }

    private static MoveService getMoveService() {
        if (moveService == null) {
            moveService = new MoveService(getBoardDao(), getPieceDao());
        }

        return moveService;
    }

    private static LoadGameService getLoadGameservice() {
        if (loadGameService == null) {
            loadGameService = new LoadGameService(getBoardDao(), getPieceDao());
        }

        return loadGameService;
    }

    public static InitGameService getInitGameService() {
        if (initGameService == null) {
            initGameService = new InitGameService(getBoardDao(), getPieceDao());
        }

        return initGameService;
    }

    private static BoardDao getBoardDao() {
        if (boardDao == null) {
            boardDao = new BoardDao();
        }

        return boardDao;
    }

    private static PieceDao getPieceDao() {
        if (pieceDao == null) {
            pieceDao = new PieceDao();
        }

        return pieceDao;
    }

    public static FetchGameController getLoadGameController() {
        if (fetchGameController == null) {
            fetchGameController = new FetchGameController(getLoadGameservice());
        }

        return fetchGameController;
    }

    public static PauseController getPauseController() {
        if (pauseController == null) {
            pauseController = new PauseController(getPauseService(), getLoadGameservice());
        }
        return pauseController;
    }

    private static PauseService getPauseService() {
        if (pauseService == null) {
            pauseService = new PauseService();
        }
        return pauseService;
    }

    public static StatusController getStatusGameController() {
        if (statusController == null) {
            statusController = new StatusController(getStatusService(), getLoadGameservice());
        }
        return statusController;
    }

    private static StatusService getStatusService() {
        if (statusService == null) {
            statusService = new StatusService(getBoardDao(), getPieceDao());
        }
        return statusService;
    }
}
