package days;

import com.google.common.primitives.Ints;

import java.util.function.Function;

public class Day10 extends Day<String[]> {

    private int cycle = 0;
    private int signalsSum = 0;
    private int x = 1;

    @Override
    Function<String, String[]> getMappingFunction() {
        return l -> l.split(" ");
    }

    @Override
    Integer getDayNumber() {
        return 10;
    }

    private void incrementCycleAndDoTheSum() {
        cycle++;

        if (Ints.contains(new int[]{ 20, 60, 100, 140, 180, 220 }, cycle))
            signalsSum += cycle * x;
    }

    @Override
    public String getResultPartOne() {
        for (String[] line : inputSupplier.get().toList()) {
            incrementCycleAndDoTheSum();

            if (line[0].equals("addx")) {
                incrementCycleAndDoTheSum();

                x += Integer.parseInt(line[1]);
            }
        }

        return String.valueOf(signalsSum);
    }

    @Override
    public String getResultPartTwo() {
        return "bruh";
    }
}
