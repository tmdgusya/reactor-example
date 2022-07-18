import org.junit.jupiter.api.Test;

import java.time.Duration;

import reactor.core.publisher.Mono;

public class MonoExample {

    @Test
    public void test() {
        Mono.firstWithValue(
                Mono.just(1).map(integer -> "foo" + integer),
                Mono.delay(Duration.ofMillis(100)).thenReturn("bar")
        ).subscribe(System.out::println);


        Mono.error(new IllegalStateException())
                .doOnError((err) -> {
                    System.out.println(err.toString());
                })
                .map((err) -> {
                    System.out.println(err);
                    return err;
                }).subscribe(System.out::println);
    }

}
