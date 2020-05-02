package lt.pauliusk.luminor.domain.payment.fee.factory;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentFee;
import lt.pauliusk.luminor.domain.payment.fee.PaymentFeeFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentFeeFactoryImpl implements PaymentFeeFactory {
    @Override
    public PaymentFee create(BigDecimal amount, Currency currency, Payment payment) {
        PaymentFee fee =  new PaymentFee();

        fee.setAmount(amount);
        fee.setCurrency(currency);
        fee.setPayment(payment);

        return fee;
    }
}
