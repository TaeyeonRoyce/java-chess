package chess;

import chess.controller.ApplicationController;

public class Application {

    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        applicationController.run();
    }
}
