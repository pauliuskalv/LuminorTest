package lt.pauliusk.luminor.rest.request.builder;

import lt.pauliusk.luminor.rest.request.RequestCompletedListener;
import lt.pauliusk.luminor.rest.request.RequestException;
import lt.pauliusk.luminor.rest.request.RestRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class WebfluxGetRequest implements RestRequest {
    private final String url;
    private final String methodTemplate;

    public WebfluxGetRequest(String url, String methodTemplate) {
        this.url = url;
        this.methodTemplate = methodTemplate;
    }

    @Override
    public <T> T start(Map<String, Object> args, Class<T> responseType) throws RequestException {
        Mono<T> request = constructRequest(args, responseType);
        return request.block();
    }

    @Override
    public <T> void startAsync(Map<String, Object> args, Class<T> responseType, RequestCompletedListener<T> listener) {
        Mono<T> request = constructRequest(args, responseType);
        request.subscribe(listener::requestComplete);
    }

    private <T> Mono<T> constructRequest(Map<String, Object> args, Class<T> responseType) {
        return WebClient
                .create(url)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(methodTemplate)
                        .build(args))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType);
    }
}
