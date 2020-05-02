package lt.pauliusk.luminor.rest.integration.geolocation.ipapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpApiGetRequestResponse {
    @JsonProperty(value = "query")
    private String query;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "countryCode")
    private String countryCode;

    @JsonProperty(value = "region")
    private String region;

    @JsonProperty(value = "regionName")
    private String regionName;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "zip")
    private String zipCode;

    @JsonProperty(value = "lat")
    private Double latitude;

    @JsonProperty(value = "lon")
    private Double longitude;

    @JsonProperty(value = "timezone")
    private String timezone;

    @JsonProperty(value = "isp")
    private String isp;

    @JsonProperty(value = "org")
    private String organization;

    @JsonProperty(value = "as")
    private String as;
}
