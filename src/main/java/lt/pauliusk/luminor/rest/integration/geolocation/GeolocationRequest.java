package lt.pauliusk.luminor.rest.integration.geolocation;

public interface GeolocationRequest {
    String getCountry(String ip) throws Exception;
    String getProviderName();
}
