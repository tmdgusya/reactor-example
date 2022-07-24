import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class Reactor3_02 {

    @Test
    public void test() {

        // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
        Flux
                .interval(Duration.ofMillis(100))
                .take(10)
                .subscribe(System.out::println);
    }

}
