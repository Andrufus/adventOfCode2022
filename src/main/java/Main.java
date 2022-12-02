import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DaysModule());

        DayService dayService = injector.getInstance(DayService.class);

        System.out.println(dayService.day().getResultPartOne());
        System.out.println(dayService.day().getResultPartTwo());
    }
}
