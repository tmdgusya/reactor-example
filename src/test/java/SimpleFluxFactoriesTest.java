import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SimpleFluxFactoriesTest {

    @Test
    public void simple() {
        // create flux whose values are in finite range
        var rangeOfInteger = Flux.range(0, 10);
        StepVerifier.create(rangeOfInteger).expectNextCount(10).verifyComplete();

        var letters = Flux.just("A", "B", "C");
        StepVerifier.create(letters).expectNext("A", "B", "C").verifyComplete();

        var now = System.currentTimeMillis();
        var greetingMono = Mono.just(new Date(now));
        StepVerifier.create(greetingMono).expectNext(new Date(now)).verifyComplete();

        var emptyMono = Mono.empty();
        StepVerifier.create(emptyMono).verifyComplete();

        var fromArray = Flux.fromArray(new Integer[] {1, 2, 3});
        StepVerifier.create(fromArray).expectNext(1, 2, 3).verifyComplete();

        var fromIterable = Flux.fromIterable(Arrays.asList(1, 2, 3));
        StepVerifier.create(fromIterable).expectNext(1, 2, 3).verifyComplete();

        var integer = new AtomicInteger(1);
        var integerFlux = Flux.fromStream(Stream.generate(integer::getAndIncrement));
        StepVerifier.create(integerFlux.take(3)).expectNext(1).expectNext(2).expectNext(3).verifyComplete();

        var flux = Flux.just("A");
        flux.map(s -> "foo" + s);
        flux.subscribe(System.out::println);

        Flux
            .just("A")
            .map(s -> "foo" + s)
            .subscribe(System.out::println);
    }

}
