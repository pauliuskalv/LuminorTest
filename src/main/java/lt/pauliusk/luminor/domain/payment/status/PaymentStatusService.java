package lt.pauliusk.luminor.domain.payment.status;

import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import lt.pauliusk.luminor.bean.payment.constant.PaymentStatusConst;
import lt.pauliusk.luminor.dao.payment.PaymentStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusService {
    private final PaymentStatusDAO paymentStatusDAO;

    public PaymentStatusService(@Autowired PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

    public PaymentStatus getPaymentStatusByCode(PaymentStatusConst paymentStatus) throws Exception {
        return paymentStatusDAO.findPaymentStatusByCode(paymentStatus.getCode()).orElseThrow(() -> new Exception("No payment status with code " + paymentStatus.getCode()));
    }
}
