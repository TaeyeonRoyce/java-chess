package chess.view.output.result;

import chess.view.output.result.MatchResultMapper;
import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import chess.domain.piece.PieceScore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResultFormatter {

    private static final String WHITE_CAMP_PREFIX = "백 : ";
    private static final String BLACK_CAMP_PREFIX = "흑 : ";

    private GameResultFormatter() {
    }

    public static List<String> convertToGameResult(GameResult gameResult) {
        List<String> result = new ArrayList<>();
        MatchResult matchResult = gameResult.getMatchResult();

        if (matchResult == MatchResult.PAUSE) {
            return Collections.emptyList();
        }

        String matchResultViewFormat = MatchResultMapper.getViewFormatBy(matchResult);
        result.add(matchResultViewFormat);

        applyCampScore(gameResult, result);
        return result;
    }

    private static void applyCampScore(GameResult gameResult, List<String> result) {
        if (gameResult.containsScore()) {
            result.add(getScoreFormatOfCamp(gameResult, Camp.WHITE));
            result.add(getScoreFormatOfCamp(gameResult, Camp.BLACK));
        }
    }

    private static String getScoreFormatOfCamp(GameResult gameResult, Camp camp) {
        StringBuilder formatBuilder = new StringBuilder();
        appendCampPrefix(camp, formatBuilder);

        PieceScore scoreOfCamp = gameResult.peekScoreOfCamp(camp);
        formatBuilder.append(scoreOfCamp.getValue());

        return formatBuilder.toString();
    }

    private static void appendCampPrefix(Camp camp, StringBuilder formatBuilder) {
        if (camp == Camp.WHITE) {
            formatBuilder.append(WHITE_CAMP_PREFIX);
            return;
        }

        formatBuilder.append(BLACK_CAMP_PREFIX);
    }
}
