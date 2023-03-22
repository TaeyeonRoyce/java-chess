package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.ChessCommandType;
import chess.controller.mapper.request.ChessCommandTypeMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChessCommandTest {

    @ParameterizedTest(name = "입력에 따라 체스의 상태가 결정됩니다. {0} - {1}")
    @CsvSource(value = {
            "start, START",
            "move, MOVE",
            "end, END",})
    void fromUserInputStartTest(String userInput, ChessCommandType expectedChessCommand) {
        ChessCommandType chessCommandType = ChessCommandTypeMapper.toChessCommandType(userInput);

        assertThat(chessCommandType).isEqualTo(expectedChessCommand);
    }

    @ParameterizedTest(name = "유효하지 않은 입력인 경우 예외가 발생합니다. - {0}")
    @ValueSource(strings = {"Start", "END", "ada", "Hello"})
    void fromUserInputExceptionTest(String userInput) {
        assertThatThrownBy(() -> ChessCommandTypeMapper.toChessCommandType(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}