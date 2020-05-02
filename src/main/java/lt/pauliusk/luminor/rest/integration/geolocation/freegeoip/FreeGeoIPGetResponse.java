package lt.pauliusk.luminor.rest.integration.geolocation.freegeoip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class FreeGeoIPGetResponse {
    @JsonProperty(value = "ip")
    private String ip;

    @JsonProperty(value = "country_code")
    private String countryCode;

    @JsonProperty(value = "country_name")
    private String countryName;

    @JsonProperty(value = "region_code")
    private String regionCode;

    @JsonProperty(value = "region_name")
    private String regionName;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "zip_code")
    private String zipCode;

    @JsonProperty(value = "time_zone")
    private String timeZone;

    @JsonProperty(value = "latitude")
    private Float latitude;

    @JsonProperty(value = "longitude")
    private Float longitude;

    @JsonProperty(value = "metro_code")
    private String metroCode;
}
