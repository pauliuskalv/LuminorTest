package lt.pauliusk.luminor.domain.payment.fee.cancellation;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import lt.pauliusk.luminor.domain.payment.fee.CancellationFee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class TypeThreeCancellationFee implements CancellationFee {
    private final BigDecimal coefficient = new BigDecimal("0.15");

    @Override
    public BigDecimal calculate(Payment payment) {
        int paymentHour = payment.getCreateDate().getHour();
        int currHour = ZonedDateTime.now().getHour();

        int hourDiff = Math.abs(paymentHour - currHour);

        return new BigDecimal(hourDiff).multiply(coefficient);
    }

    @Override
    public boolean isApplicable(Payment payment) {
        return PaymentTypeConst.TYPE_THREE.getCode().equals(payment.getPaymentType().getCode());
    }
}
