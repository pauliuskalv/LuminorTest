package lt.pauliusk.luminor.startup.database;

import lt.pauliusk.luminor.bean.log.PaymentNotificationStatus;
import lt.pauliusk.luminor.bean.log.constant.PaymentNotificationStatusConst;
import lt.pauliusk.luminor.dao.log.PaymentNotificationStatusDAO;
import lt.pauliusk.luminor.startup.DatabasePopulateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentNotificationStatusPopulateTask implements DatabasePopulateTask {
    private final PaymentNotificationStatusDAO paymentNotificationStatusDAO;

    public PaymentNotificationStatusPopulateTask(@Autowired PaymentNotificationStatusDAO paymentNotificationStatusDAO) {
        this.paymentNotificationStatusDAO = paymentNotificationStatusDAO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PaymentNotificationStatus statusSuccess = new PaymentNotificationStatus();
        statusSuccess.setCode(PaymentNotificationStatusConst.SUCCESS.getCode());

        PaymentNotificationStatus statusFailure = new PaymentNotificationStatus();
        statusFailure.setCode(PaymentNotificationStatusConst.FAILED.getCode());

        paymentNotificationStatusDAO.save(statusSuccess);
        paymentNotificationStatusDAO.save(statusFailure);
    }
}
