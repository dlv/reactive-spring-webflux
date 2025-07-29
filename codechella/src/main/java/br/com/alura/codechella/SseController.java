package br.com.alura.codechella;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
public class SseController {

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:5500", methods = { RequestMethod.GET }) // Apenas para este método
    public Flux<String> streamEvents() {
        return Flux.fromStream(Stream.generate(() -> "Evento: " + System.currentTimeMillis()))
                .delayElements(Duration.ofSeconds(1));
    }
}
