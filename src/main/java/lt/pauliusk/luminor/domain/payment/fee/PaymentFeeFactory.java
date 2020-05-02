package lt.pauliusk.luminor.domain.payment.fee;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentFee;

import java.math.BigDecimal;

public interface PaymentFeeFactory {
    PaymentFee create(BigDecimal amount, Currency currency, Payment payment);
}
