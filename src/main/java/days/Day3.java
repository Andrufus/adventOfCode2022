package days;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.function.Function;

public class Day3 extends Day<List<List<String>>> {

    private final List<String> letters = List.of(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");


    @Override
    Function<String, List<List<String>>> getMappingFunction() {
        return l -> Splitter.fixedLength(l.length() / 2).splitToStream(l)
                .map(s -> Splitter.fixedLength(1).splitToList(s))
                .toList();
    }

    @Override
    Integer getDayNumber() {
        return 3;
    }

    @Override
    public String getResultPartOne() {
        Integer total = inputSupplier.get()
                .map(rucksacks -> {
                    var compartment1 = rucksacks.get(0);
                    var compartment2 = rucksacks.get(1);
                    return compartment1.stream().filter(compartment2::contains).toList().get(0);
                })
                .map(item -> letters.indexOf(item) + 1)
                .reduce(Integer::sum)
                .orElseThrow();

        return "Sum of the priorities : " + total;
    }

    @Override
    public String getResultPartTwo() {
        return null;
    }
}
