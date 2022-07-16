import org.junit.jupiter.api.Test;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;

import java.util.concurrent.Flow;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FlowAndReactiveStreamsTest {

    @Test
    public void simple() {
        Flux<Integer> range = Flux.range(0, 10);
        Flow.Publisher<Integer> jdk9Publisher = FlowAdapters.toFlowPublisher(range);
        Publisher<Integer> reactiveSpecificPublisher = FlowAdapters.toPublisher(jdk9Publisher);

        StepVerifier.create(range).expectNextCount(10).verifyComplete();
        StepVerifier.create(reactiveSpecificPublisher).expectNextCount(10).verifyComplete();
    }

}
