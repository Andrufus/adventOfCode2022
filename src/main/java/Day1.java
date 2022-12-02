import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;


public class Day1 {

    public static void main(String[] args) throws IOException {
        System.out.println("It's the first day !");

        ArrayList<Integer> sums = new ArrayList<>();
        var currentSum = 0;

        for (String line : Files.readAllLines(FileUtils.getInputAsPath(1))) {
            if (line.isEmpty()) {
                sums.add(currentSum);
                currentSum = 0;
            }
            else currentSum += Integer.parseInt(line);
        }

        System.out.println("Max : " + sums.stream().max(Integer::compareTo).orElseThrow());

        sums.sort(Collections.reverseOrder());
        System.out.println("Top 3 : " + sums.subList(0, 3).stream().reduce(Integer::sum).orElseThrow());
    }
}
