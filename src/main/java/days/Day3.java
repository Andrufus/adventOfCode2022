package days;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day3 extends Day<List<String>> {

    private final List<String> letters = List.of(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");


    @Override
    Function<String, List<String>> getMappingFunction() {
        return l ->  Splitter.fixedLength(1).splitToList(l);
    }

    @Override
    Integer getDayNumber() {
        return 3;
    }

    @Override
    public String getResultPartOne() {
        Stream<String> total = inputSupplier.get()
                .map(rucksack -> Lists.partition(rucksack, rucksack.size() / 2))
                .map(splitRucksack -> {
                    var compartment1 = splitRucksack.get(0);
                    var compartment2 = splitRucksack.get(1);
                    return compartment1.stream().filter(compartment2::contains).toList().get(0);
                });

        return "Sum of the priorities : " + mapToIntAndSum(total);
    }

    @Override
    public String getResultPartTwo() {
        Stream<String> total = Streams.stream(Iterables.partition(inputSupplier.get().toList(), 3))
                .map(groupedRucksacks -> {
                    var sack1 = groupedRucksacks.get(0);
                    var sack2 = groupedRucksacks.get(1);
                    var sack3 = groupedRucksacks.get(2);
                    return sack1.stream().filter(sack2::contains).filter(sack3::contains).toList().get(0);
                });

        return "Sum of the priorities : " + mapToIntAndSum(total);
    }

    private Integer mapToIntAndSum(Stream<String> letter) {
        return letter
                .map(item -> letters.indexOf(item) + 1)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
