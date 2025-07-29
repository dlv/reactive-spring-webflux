package br.com.alura.codechella;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TraducaoDeTextos {

    public static Mono<String> obterTraducao(String texto, String idioma) {
        Dotenv dotenv = Dotenv.load();
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api-free.deepl.com/v2/translate")
                .build();

        MultiValueMap<String, String> req = new LinkedMultiValueMap<>();

        req.add("text", texto);
        req.add("target_lang", idioma);

        String apiKey = dotenv.get("DEEPL_APIKEY");

        return webClient.post()
                .header("Authorization", "DeepL-Auth-Key " + apiKey)
                .body(BodyInserters.fromFormData(req))
                .retrieve()
                .bodyToMono(Traducao.class)
                .map(Traducao::getTexto);
    }
}
