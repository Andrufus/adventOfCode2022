package days;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Day5 extends Day<Day5.Move> {

    private final Pattern pattern = Pattern.compile("\\d+");

    static class Move {
        Integer quantity;
        Integer from;
        Integer to;

        public Move(Integer quantity, Integer from, Integer to) {
            this.quantity = quantity;
            this.from = from;
            this.to = to;
        }
    }

    private final Map<Integer, Deque<String>> stacks = Map.of(
        1, new LinkedList<>(List.of("P", "G", "R", "N")),
        2, new LinkedList<>(List.of("C", "D", "G", "F", "L", "B", "T", "J")),
        3, new LinkedList<>(List.of("V", "S", "M")),
        4, new LinkedList<>(List.of("P", "Z", "C", "R", "S", "L")),
        5, new LinkedList<>(List.of("Q", "D", "W", "C", "V", "L", "S", "P")),
        6, new LinkedList<>(List.of("S", "M", "D", "W", "N", "T", "C")),
        7, new LinkedList<>(List.of("P", "W", "G", "D", "H")),
        8, new LinkedList<>(List.of("V", "M", "C", "S", "H", "P", "L", "Z")),
        9, new LinkedList<>(List.of("Z", "G", "W", "L", "F", "P", "R"))
    );

    @Override
    Function<String, Move> getMappingFunction() {
        return line -> {
            List<Integer> theNumbers = pattern.matcher(line)
                    .results()
                    .map(matchResult -> Integer.parseInt(matchResult.group(0)))
                    .toList();
            return new Move(theNumbers.get(0), theNumbers.get(1), theNumbers.get(2));
        };
    }

    private String getTopCrates() {
        StringBuilder result = new StringBuilder("Top crates : ");
        for (int i = 1; i <= 9; i++) {
            result.append(stacks.get(i).getFirst());
        }

        return result.toString();
    }

    @Override
    Integer getDayNumber() {
        return 5;
    }

    //NB : Don't run the two parts one after the other since they use the same Map
    @Override
    public String getResultPartOne() {
        inputSupplier.get()
                .forEach(move -> {
                    for (int i = 0; i < move.quantity; i++) {
                        stacks.get(move.to).addFirst(stacks.get(move.from).removeFirst());
                    }
                });

        return getTopCrates();
    }

    @Override
    public String getResultPartTwo() {
        inputSupplier.get()
                .forEach(move -> {
                    Deque<String> toMove = new LinkedList<>();
                    for (int i = 0; i < move.quantity; i++) {
                        toMove.addFirst(stacks.get(move.from).removeFirst());
                    }
                    toMove.forEach(s -> stacks.get(move.to).addFirst(s));
                });

        return getTopCrates();
    }
}
