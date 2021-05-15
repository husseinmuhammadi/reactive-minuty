package com.javastudio.tutorial;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniSubscribe;
import io.smallrye.mutiny.subscription.Cancellable;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class UniTest {
    @Test
    public void name() {
        AtomicInteger counter = new AtomicInteger();
        Uni<Integer> uni = Uni.createFrom().item(() -> counter.getAndIncrement());

//        uni.subscribe().with(System.out::println);
//        uni.subscribe().with(System.out::println);
//        uni.subscribe().with(System.out::println);
//        uni.subscribe().with(System.out::println);
//        uni.subscribe().with(System.out::println);

        System.out.println("Start");


        Uni<String> shortDelayResponse = uni.onItem().transform(i -> "#" + i)
                .onItem().delayIt().by(Duration.ofMillis(2000));

        Uni<String> longDelayResponse = uni.onItem().transform(i -> "#" + i)
                .onItem().delayIt().by(Duration.ofMillis(5000));


        shortDelayResponse
                .ifNoItem().after(Duration.ofSeconds(500))
                .fail()
                .subscribe().with(System.out::println);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        uni.onItem().transform(i -> "#" + i)
//                .onItem().delayIt().by(Duration.ofMillis(100))
//                .subscribe().with(System.out::println);
    }
}
