package days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;


public class Day1 extends Day<String> {

    private final ArrayList<Integer> sums = new ArrayList<>();

    public Day1() {
        var currentSum = 0;

        for (String line : inputSupplier.get().toList()) {
            if (line.isEmpty()) {
                sums.add(currentSum);
                currentSum = 0;
            }
            else currentSum += Integer.parseInt(line);
        }
    }

    @Override
    Function<String, String> getMappingFunction() {
        return l -> l;
    }

    @Override
    Integer getDayNumber() {
        return 1;
    }

    @Override
    public String getResultPartOne() {
        return "Max : " + sums.stream().max(Integer::compareTo).orElseThrow();
    }

    @Override
    public String getResultPartTwo() {
        return "Top 3 : " + sums.stream().sorted(Collections.reverseOrder())
                .limit(3).reduce(Integer::sum).orElseThrow();
    }
}
