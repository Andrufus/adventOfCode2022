package days;

import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Day4 extends Day<List<Integer>> {
    @Override
    Function<String, List<Integer>> getMappingFunction() {
        return line -> Arrays.stream(line.split(",|-"))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    Integer getDayNumber() {
        return 4;
    }

    private long filterAndCount(BiPredicate<Range<Integer>, Range<Integer>> filter) {
        return inputSupplier.get()
                .map(pairs -> new Range[] {
                        Range.closed(pairs.get(0), pairs.get(1)),
                        Range.closed(pairs.get(2), pairs.get(3))
                })
                .filter(ranges -> filter.test(ranges[0], ranges[1]))
                .count();
    }

    @Override
    public String getResultPartOne() {
        return "Assignment pairs with one range fully containing the other : "
                + filterAndCount((range1, range2) ->  range1.encloses(range2) || range2.encloses(range1));
    }

    @Override
    public String getResultPartTwo() {
        return "Assignment pairs with ranges overlapping : "
                + filterAndCount(Range::isConnected);
    }
}
