import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class Day2 {

    // PART ONE DATA
    private static final Map<String, Integer> CHOICES_POINTS = Map.of(
        "X", 1,
        "Y", 2,
        "Z", 3
    );

    private static final Map<String, String> DRAW = Map.of(
        "A", "X",
        "B", "Y",
        "C", "Z"
    );

    private static final Map<String, String> OPPONENT_WINS = Map.of(
        "A", "Z",
        "B", "X",
        "C", "Y"
    );

    // PART TWO DATA
    private static final Map<String, Integer> MY_CHOICES_POINTS = Map.of(
        "A", 1,
        "B", 2,
        "C", 3
    );

    private static final Map<String, Integer> RESULT_POINTS = Map.of(
        "X", 0,
        "Y", 3,
        "Z", 6
    );

    private static final Map<String, String[]> WHO_WOULD_WIN = Map.of(
        "A", new String[] { "B", "C" },
        "B", new String[] { "C", "A" },
        "C", new String[] { "A", "B" }
    );

    private static Integer computeRoundScore(String opponent, String me) {
        if (DRAW.get(opponent).equals(me))
            return 3;
        else if (OPPONENT_WINS.get(opponent).equals(me))
            return 0;
        else
            return 6;
    }

    private static String chooseShape(String opponentChoice, String expectedResult) {
        return switch (expectedResult) {
            case "X" -> WHO_WOULD_WIN.get(opponentChoice)[1];
            case "Y" -> opponentChoice;
            case "Z" -> WHO_WOULD_WIN.get(opponentChoice)[0];
            default -> throw new IllegalStateException("Unexpected value: " + expectedResult);
        };
    }

    private static Integer getTotalScore(Supplier<Stream<String[]>> strategySupplier, Function<String[], Integer> function) {
        return strategySupplier.get()
            .map(function)
            .reduce(Integer::sum)
            .orElseThrow();
    }

    public static void main(String[] args) {
        Supplier<Stream<String[]>> strategySupplier = () -> {
            try {
                return Files.lines(FileUtils.getInputAsPath(2))
                    .map(line -> line.split(" "));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Integer totalScorePartOne = getTotalScore(strategySupplier,
            round -> computeRoundScore(round[0], round[1]) + CHOICES_POINTS.get(round[1]));

        System.out.println("Total score part one : " + totalScorePartOne);

        Integer totalScorePartTwo = getTotalScore(strategySupplier,
            round -> RESULT_POINTS.get(round[1]) + MY_CHOICES_POINTS.get(chooseShape(round[0], round[1])));

        System.out.println("Total score part two : " + totalScorePartTwo);
    }
}
