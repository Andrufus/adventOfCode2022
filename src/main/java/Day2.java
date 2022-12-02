import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;


public class Day2 {

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

    private static Integer computeRoundScore(String opponent, String me) {
        if (DRAW.get(opponent).equals(me))
            return 3;
        else if (OPPONENT_WINS.get(opponent).equals(me))
            return 0;
        else
            return 6;
    }

    public static void main(String[] args) throws IOException {
        Integer totalScore = Files.lines(FileUtils.getInputAsPath(2))
            .map(line -> line.split(" "))
            .map(choices -> computeRoundScore(choices[0], choices[1]) + CHOICES_POINTS.get(choices[1]))
            .reduce(Integer::sum)
            .orElseThrow();

        System.out.println("Total score : " + totalScore);
    }
}
