import com.google.inject.Inject;
import com.google.inject.name.Named;
import days.Day;

public record DayService(Day day) {

    private static final String DAY_NUMBER = "day6";

    @Inject
    public DayService(@Named(DAY_NUMBER) Day day) {
        this.day = day;
    }
}
