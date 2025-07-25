package br.com.alura.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping //(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> obterTodos() {

        return service.obterTodos();
    }

    @GetMapping("/{id}") //(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<EventoDto> obterPorId(@PathVariable Long id) {

        return service.obterPorId(id);
    }

    @PostMapping
    public Mono<EventoDto> cadastrar(@RequestBody EventoDto dto){
        return service.cadastrar(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> excluir(@PathVariable Long id) {
        return service.excluir(id);

    }

    @PutMapping("/{id}")
    public Mono<EventoDto> atualizar(@PathVariable Long id, @RequestBody EventoDto dto){
        return service.atualizar(id, dto);
    }
}
