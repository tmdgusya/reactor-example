import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Reactor3_05 {

    @Test
    public void test() {

        Mono
                .just(User.JESSE)
                .map((user) ->
                        new User(
                                user.getUsername().toUpperCase(),
                                user.getFirstname().toUpperCase(),
                                user.getLastname().toUpperCase()
                        )
                );

        var flux = Flux
                .just(User.JESSE)
                .flatMap(user ->
                     Flux.just(new User(
                             user.getUsername().toUpperCase(),
                             user.getFirstname().toUpperCase(),
                             user.getLastname().toUpperCase()
                 ))
         );

         flux.subscribe(System.out::println);



    }

}
