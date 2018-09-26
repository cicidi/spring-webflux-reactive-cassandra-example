package com.cicidi.springwebflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@Configuration
public class ReactiveClientExample implements CommandLineRunner {

    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<>();
        config.put("server.port", "8081");
        config.put("logging.level.root", "info");
        new SpringApplicationBuilder(ReactiveClientExample.class)
                .properties(config)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.get()
                .uri("/people/find?firstName=FirstName_1&lastName=LastName_1")
                .header("Content-Type", "application/json")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .map(x -> x.bodyToFlux(Object.class)).block()
                .subscribe(x -> System.out.println("&&&&&&&" + x.toString()));
    }
}