package lt.pauliusk.luminor.rest.integration.notification;

import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;

public interface PaymentNotificationListener {
    void notify(PaymentDTO payment) throws Exception;
    String getUrl();
}
