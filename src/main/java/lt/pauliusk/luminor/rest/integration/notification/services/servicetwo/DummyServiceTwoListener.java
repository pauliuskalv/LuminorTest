package lt.pauliusk.luminor.rest.integration.notification.services.servicetwo;

import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import lt.pauliusk.luminor.rest.integration.notification.PaymentNotificationListener;
import lt.pauliusk.luminor.rest.request.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DummyServiceTwoListener implements PaymentNotificationListener {
    private static final String URL = "http://localhost:8080";
    private static final String METHOD_TEMPLATE = "/dummyservicetwo/notify";
    private final RequestBuilder requestBuilder;

    public DummyServiceTwoListener(@Autowired RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    public void notify(PaymentDTO payment) throws Exception {
        requestBuilder.buildPostRequest(URL, METHOD_TEMPLATE, payment).start(Map.of(), PaymentDTO.class);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
