package lt.pauliusk.luminor.rest.request.builder;

import lt.pauliusk.luminor.rest.request.RequestBuilder;
import lt.pauliusk.luminor.rest.request.RestRequest;
import org.springframework.stereotype.Service;

@Service
public class WebfluxRequestBuilder implements RequestBuilder {
    @Override
    public RestRequest buildGetRequest(String url, String methodTemplate) {
        return new WebfluxGetRequest(url, methodTemplate);
    }

    @Override
    public RestRequest buildPostRequest(String url, String methodTemplate) {
        return new WebfluxPostRequest(url, methodTemplate);
    }

    @Override
    public RestRequest buildPostRequest(String url, String methodTemplate, Object body) {
        return new WebfluxPostRequest(url, methodTemplate, body);
    }
}
