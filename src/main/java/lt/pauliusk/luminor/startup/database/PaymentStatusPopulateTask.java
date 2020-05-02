package lt.pauliusk.luminor.startup.database;

import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import lt.pauliusk.luminor.bean.payment.constant.PaymentStatusConst;
import lt.pauliusk.luminor.dao.payment.PaymentStatusDAO;
import lt.pauliusk.luminor.startup.DatabasePopulateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentStatusPopulateTask implements DatabasePopulateTask {
    private final PaymentStatusDAO paymentStatusDAO;

    public PaymentStatusPopulateTask(@Autowired PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PaymentStatus paymentStatusNew = new PaymentStatus();
        paymentStatusNew.setName("New payment");
        paymentStatusNew.setCode(PaymentStatusConst.NEW.getCode());

        PaymentStatus paymentStatusProcessed = new PaymentStatus();
        paymentStatusProcessed.setName("Payment processed");
        paymentStatusProcessed.setCode(PaymentStatusConst.PROCESSED.getCode());

        PaymentStatus paymentStatusCancelled = new PaymentStatus();
        paymentStatusCancelled.setName("Payment cancelled");
        paymentStatusCancelled.setCode(PaymentStatusConst.CANCELLED.getCode());

        paymentStatusDAO.save(paymentStatusNew);
        paymentStatusDAO.save(paymentStatusProcessed);
        paymentStatusDAO.save(paymentStatusCancelled);
    }
}
