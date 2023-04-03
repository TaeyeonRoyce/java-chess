package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public enum InputView {
;
    private static final String MOVE_DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCommands() {
        return Arrays.asList(scanner.nextLine().split(MOVE_DELIMITER));
    }
}
