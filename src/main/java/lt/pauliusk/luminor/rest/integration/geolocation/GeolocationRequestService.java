package lt.pauliusk.luminor.rest.integration.geolocation;

import lt.pauliusk.luminor.rest.request.RequestCompletedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GeolocationRequestService {
    private final Logger logger = Logger.getLogger(GeolocationRequestService.class.getName());
    private final Executor asyncExecutor = Executors.newSingleThreadExecutor();

    private final List<GeolocationRequest> providers;

    public GeolocationRequestService(@Autowired List<GeolocationRequest> providers) {
        this.providers = providers;
    }

    public void getCountryAsync(String recordedIp, RequestCompletedListener<String> listener) {
        asyncExecutor.execute(() -> {
            try {
                listener.requestComplete(getCountry(recordedIp));
            } catch (Exception e) {
                logger.log(Level.WARNING, "Geolocation request failed");
            }
        });
    }

    public String getCountry(String recordedIp) throws Exception {
        if (providers.isEmpty()) {
            throw new Exception("No providers for geolocation!");
        }

        for (GeolocationRequest request : providers) {
            try {
                logger.info("Trying " + request.getProviderName());
                return request.getCountry(recordedIp);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Geolocation provider " + request.getProviderName() + " failed", e);
            }
        }

        logger.log(Level.SEVERE, "All geolocation providers failed!");

        return null;
    }
}
