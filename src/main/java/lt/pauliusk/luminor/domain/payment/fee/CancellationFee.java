package lt.pauliusk.luminor.domain.payment.fee;

import lt.pauliusk.luminor.bean.payment.Payment;

import java.math.BigDecimal;

public interface CancellationFee {
    BigDecimal calculate(Payment payment);
    boolean isApplicable(Payment payment);
}
