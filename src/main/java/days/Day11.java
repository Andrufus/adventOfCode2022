package days;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day11 extends Day<String> {

    static class Monkey {
        Integer number;
        Deque<Long> items;
        Function<Long, Long> operation;
        Integer test;
        Integer toTrue;
        Integer toFalse;
        Integer inspections = 0;
    }

    private final Map<Integer, Monkey> monkeys;

    private int mathThing = 1;

    public Day11() {
        monkeys = Lists.partition(inputSupplier.get()
                        .filter(line -> !line.isEmpty())
                        .toList(), 6)
                .stream()
                .map(monkeyList -> {
                    Monkey monkey = new Monkey();
                    monkey.number = getRegexResults(monkeyList.get(0)).get(0);
                    monkey.items = new LinkedList<>(getRegexResults(monkeyList.get(1)).stream().map(Long::valueOf).toList());

                    try {
                        String op = getOpRegexResult(monkeyList, 1);
                        Integer opNum = Integer.parseInt(getOpRegexResult(monkeyList, 2));
                        monkey.operation = (Long old) -> op.equals("*") ? old * opNum : old + opNum;
                    } catch (NoSuchElementException e) {
                        monkey.operation = (Long old) -> old * old;
                    }

                    monkey.test = getRegexResults(monkeyList.get(3)).get(0);
                    mathThing *= monkey.test;
                    monkey.toTrue = getRegexResults(monkeyList.get(4)).get(0);
                    monkey.toFalse = getRegexResults(monkeyList.get(5)).get(0);
                    return monkey;
                })
                .collect(Collectors.toMap(monkey -> monkey.number, monkey -> monkey));
    }

    private static String getOpRegexResult(List<String> monkeyList, Integer group) {
        return Pattern.compile("(\\*|\\+) (\\d{1,2})")
                .matcher(monkeyList.get(2))
                .results()
                .map(matchResult -> matchResult.group(group))
                .findFirst()
                .orElseThrow();
    }

    private static List<Integer> getRegexResults(String monkeyLine) {
        return Pattern.compile("\\d+")
                .matcher(monkeyLine)
                .results()
                .map(matchResult -> Integer.parseInt(matchResult.group(0)))
                .toList();
    }

    @Override
    Function<String, String> getMappingFunction() {
        return l -> l;
    }

    @Override
    Integer getDayNumber() {
        return 11;
    }

    private Long getMonkeyBusiness(Integer rounds, boolean reduceWorry) {
        for (int i = 0; i < rounds; i++) {
            monkeys.forEach((number, monkey) -> {
                while (!monkey.items.isEmpty()) {
                    monkey.inspections++;
                    Long item = monkey.operation.apply(monkey.items.removeFirst());

                    if (reduceWorry)
                        item /= 3;
                    else
                        item %= mathThing;

                    monkeys.get(item % monkey.test == 0 ? monkey.toTrue : monkey.toFalse).items.addLast(item);
                }
            });
        }
        return monkeys.values().stream()
                .map(monkey -> Long.valueOf(monkey.inspections))
                .sorted(Collections.reverseOrder())
                .limit(2)
                .reduce((i1, i2) -> i1 * i2)
                .orElseThrow();
    }

    @Override
    public String getResultPartOne() {
        return String.valueOf(getMonkeyBusiness(20, true));
    }

    @Override
    public String getResultPartTwo() {
        return String.valueOf(getMonkeyBusiness(10_000, false));
    }
}
