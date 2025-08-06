package br.com.alura.codechella;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;
    private final Sinks.Many<EventoDto> eventoSink;

    public EventoController(EventoService service) {
        this.service = service;
        this.eventoSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping //(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> obterTodos() {

        return service.obterTodos();
    }

    @GetMapping(value = "/categoria/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:5500", methods = { RequestMethod.GET })
    public Flux<EventoDto> obterPorTipo(@PathVariable String tipo) {
        return Flux.merge(service.obterPorTipo(tipo), eventoSink.asFlux())
                .delayElements(Duration.ofSeconds(4));
    }

    @GetMapping("/{id}") //(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<EventoDto> obterPorId(@PathVariable Long id) {

        return service.obterPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EventoDto> cadastrar(@RequestBody EventoDto dto){

        return service.cadastrar(dto)
                .doOnSuccess(e -> eventoSink.tryEmitNext(e));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> excluir(@PathVariable Long id) {
        return service.excluir(id);

    }

    @PutMapping("/{id}")
    public Mono<EventoDto> atualizar(@PathVariable Long id, @RequestBody EventoDto dto){
        return service.atualizar(id, dto);
    }

    @GetMapping("/{id}/traduzir/{idioma}")
    public Mono<String> obterTraducao(@PathVariable Long id, @PathVariable String idioma) {
        return service.obterTraducao(id, idioma);
    }
}
