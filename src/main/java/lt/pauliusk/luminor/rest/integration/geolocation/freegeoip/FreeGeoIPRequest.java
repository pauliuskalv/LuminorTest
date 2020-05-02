package lt.pauliusk.luminor.rest.integration.geolocation.freegeoip;

import lt.pauliusk.luminor.rest.integration.geolocation.GeolocationRequest;
import lt.pauliusk.luminor.rest.request.RequestBuilder;
import lt.pauliusk.luminor.rest.request.RequestException;
import lt.pauliusk.luminor.rest.request.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FreeGeoIPRequest implements GeolocationRequest {
    private static final String URL = "https://freegeoip.app";
    private static final String METHOD_TEMPLATE = "/{format}/{ip}";

    private final RequestBuilder requestBuilder;

    public FreeGeoIPRequest(@Autowired RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    public String getCountry(String ip) throws RequestException {
        RestRequest request = requestBuilder.buildGetRequest(URL, METHOD_TEMPLATE);

        FreeGeoIPGetResponse response = request.start(
                Map.of(
                        "format", "json",
                        "ip", ip
                ), FreeGeoIPGetResponse.class
        );

        return response.getCountryName();
    }

    @Override
    public String getProviderName() {
        return URL;
    }
}
