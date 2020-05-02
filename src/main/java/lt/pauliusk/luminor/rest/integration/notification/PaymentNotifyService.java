package lt.pauliusk.luminor.rest.integration.notification;

import lt.pauliusk.luminor.bean.log.PaymentNotificationLog;
import lt.pauliusk.luminor.bean.log.constant.PaymentNotificationStatusConst;
import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.dao.log.PaymentNotificationLogDAO;
import lt.pauliusk.luminor.dao.log.PaymentNotificationStatusDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PaymentNotifyService {
    private final Logger logger = Logger.getLogger(PaymentNotifyService.class.getName());

    private final List<PaymentNotificationListener> listeners;
    private final PaymentNotificationLogDAO paymentNotificationLogDAO;
    private final PaymentNotificationStatusDAO paymentNotificationStatusDAO;
    private final BeanConverter<Payment, PaymentDTO> paymentConverter;

    private final Executor notificationExecutor = Executors.newSingleThreadExecutor();

    public PaymentNotifyService(
            @Autowired List<PaymentNotificationListener> listeners,
            @Autowired PaymentNotificationLogDAO paymentNotificationLogDAO,
            @Autowired PaymentNotificationStatusDAO paymentNotificationStatusDAO,
            @Autowired BeanConverter<Payment, PaymentDTO> paymentConverter
    ) {
        this.listeners = listeners;
        this.paymentNotificationLogDAO = paymentNotificationLogDAO;
        this.paymentNotificationStatusDAO = paymentNotificationStatusDAO;
        this.paymentConverter = paymentConverter;
    }

    public void notifyAsync(Payment payment) {
        notificationExecutor.execute(() -> notify(payment));
    }

    public void notify(Payment payment) {
        for (PaymentNotificationListener listener : listeners) {
            notify(payment, listener);
        }
    }

    private void notify(Payment payment, PaymentNotificationListener listener) {
        PaymentNotificationLog log = constructLog(payment, listener);

        try {
            listener.notify(paymentConverter.convertBeanToDTO(payment));

            log.setNotificationStatus(
                    paymentNotificationStatusDAO.findPaymentNotificationStatusByCode(
                            PaymentNotificationStatusConst.SUCCESS.getCode()
                    ).get()
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to notify " + listener.getUrl());
            log.setNotificationStatus(
                    paymentNotificationStatusDAO.findPaymentNotificationStatusByCode(
                            PaymentNotificationStatusConst.FAILED.getCode()
                    ).get()
            );
        }

        paymentNotificationLogDAO.save(log);
    }

    private PaymentNotificationLog constructLog(Payment payment, PaymentNotificationListener listener) {
        PaymentNotificationLog log = new PaymentNotificationLog();

        log.setDate(ZonedDateTime.now());
        log.setUrl(listener.getUrl());
        log.setPayment(payment);

        return log;
    }
}
