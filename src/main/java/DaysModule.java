import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import days.Day;
import days.Day1;
import days.Day2;

import java.util.Map;

public class DaysModule extends AbstractModule {

    private final Map<String, Class<? extends Day>> days = Map.of(
        "day1", Day1.class,
        "day2", Day2.class
    );

    @Override
    protected void configure() {
        days.forEach((name, dayClass) -> bind(Day.class)
                .annotatedWith(Names.named(name))
                .to(dayClass));
    }
}
