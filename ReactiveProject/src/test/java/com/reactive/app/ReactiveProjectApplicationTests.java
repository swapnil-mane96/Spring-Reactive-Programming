package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@SpringBootTest
class ReactiveProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	
	/*
	 * Mono zip() method
	 * Mono zipWith() method
	 */
	@Test
	void workingWithMono() {
		Mono<String> m1 = Mono
				.just("In Mono m1")
				.log();
		
		Mono<String> m2 = Mono
				.just("In Mono m2")
				.log();
		
		// zip method
		Mono<Tuple2<String, String>> zipMono = Mono.zip(m1, m2);
		zipMono.subscribe(data ->{
			System.out.println("Zip method data is: "+data);
		});
		
		// zipWith method
		Mono<Tuple2<String,String>> zipWith = m1.zipWith(m2);
		zipWith.subscribe(data -> {
			System.out.println("ZipWith method data is: "+data);
		});
		
		// map, it applies sync function and perform some operation on Mono 
		Mono<String> resultMap = m1.map(valueMap -> valueMap.toUpperCase());
		resultMap.subscribe(data -> {
			System.out.println("Map data in Upper case: "+data);
		});
		
		// flatMap, use when data want in Async way
		Mono<String> flatMap = m1.flatMap(flatM -> Mono.just(flatM.toLowerCase()));
		flatMap.subscribe(data -> {
			System.out.println("FlatMap data is: "+data);
		});
		
		// flatMapMany, use when there are multiple streaming elements
		Flux<String> flatMapMany = m2.flatMapMany(fMany -> Flux.just(fMany.toLowerCase()));
		flatMapMany.subscribe(data -> {
			System.out.println("flatMapMany data is: "+data);
		});
		
		// concatWith() method. it is instance method
		Flux<String> concatWith = m1.concatWith(m2);
		concatWith.subscribe(data -> {
			System.out.print(" "+data);
		});
	}

}
