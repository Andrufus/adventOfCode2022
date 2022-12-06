package days;

import java.util.function.Function;
import java.util.regex.Pattern;

public class Day6 extends Day<String> {

    private final String theLine;

    public Day6() {
        this.theLine = inputSupplier.get().toList().get(0);
    }

    @Override
    Function<String, String> getMappingFunction() {
        return s -> s;
    }

    @Override
    Integer getDayNumber() {
        return 6;
    }

    private String doTheRegexThing(String regex) {
        return String.valueOf(
                Pattern.compile(regex)
                        .matcher(theLine)
                        .results()
                        .findFirst()
                        .orElseThrow()
                        .end());
    }

    @Override
    public String getResultPartOne() {
        return doTheRegexThing("(.)((?!\\1).)((?!\\1|\\2).)((?!\\1|\\2|\\3).)");
    }

    @Override
    public String getResultPartTwo() {
        return doTheRegexThing(
                "(.)((?!\\1).)" +
                "((?!\\1|\\2).)" +
                "((?!\\1|\\2|\\3).)" +
                "((?!\\1|\\2|\\3|\\4).)" +
                "((?!\\1|\\2|\\3|\\4|\\5).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8|\\9).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8|\\9|\\10).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8|\\9|\\10|\\11).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8|\\9|\\10|\\11|\\12).)" +
                "((?!\\1|\\2|\\3|\\4|\\5|\\6|\\7|\\8|\\9|\\10|\\11|\\12|\\13).)");
    }
}
