package lt.pauliusk.luminor.domain.payment.fee.cancellation;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import lt.pauliusk.luminor.domain.payment.fee.CancellationFee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

@Service
public class TypeOneCancellationFee implements CancellationFee {
    private final BigDecimal coefficient = new BigDecimal("0.05");

    @Override
    public BigDecimal calculate(Payment payment) {
        long hourDiff = Duration.between(payment.getCreateDate(), ZonedDateTime.now()).toHours();

        return new BigDecimal(hourDiff).multiply(coefficient);
    }

    @Override
    public boolean isApplicable(Payment payment) {
        return PaymentTypeConst.TYPE_ONE.getCode().equals(payment.getPaymentType().getCode());
    }
}
