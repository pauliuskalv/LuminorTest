package lt.pauliusk.luminor.rest.request;

public interface RequestBuilder {
    RestRequest buildGetRequest(String url, String methodTemplate);

    RestRequest buildPostRequest(String url, String methodTemplate);
    RestRequest buildPostRequest(String url, String methodTemplate, Object body);
}
