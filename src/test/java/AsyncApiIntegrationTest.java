import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class AsyncApiIntegrationTest {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void async() {
        Flux<Integer> integerFlux = Flux.create(emitter -> this.launch(emitter, 5));

        integerFlux
                .take(3)
                .map(this::multiply)
                .subscribe(System.out::println);
    }

    private int multiply(int num) {
        return num * num;
    }

    private void launch(FluxSink<Integer> integerFluxSink, int count) {
        this.executorService.submit(() -> {
            var integer = new AtomicInteger();
            Assertions.assertNotNull(integerFluxSink);
            while (integer.get() < count) {
                var random = Math.random();
                integerFluxSink.next(integer.getAndIncrement());
                sleep((long) random * 1_000);
            }
            integerFluxSink.complete();
        });
    }

    private void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }

}
