import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import days.*;

import java.util.Map;

public class DaysModule extends AbstractModule {

    private final Map<String, Class<? extends Day>> days = Map.of(
        "day1", Day1.class,
        "day2", Day2.class,
        "day3", Day3.class,
        "day4", Day4.class
    );

    @Override
    protected void configure() {
        days.forEach((name, dayClass) -> bind(Day.class)
                .annotatedWith(Names.named(name))
                .to(dayClass));
    }
}
