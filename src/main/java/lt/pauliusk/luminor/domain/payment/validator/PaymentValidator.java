package lt.pauliusk.luminor.domain.payment.validator;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.domain.Validator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentValidator implements Validator<Payment> {
    @Override
    public boolean isValid(Payment bean) {
        if (bean.getAmount() == null) {
            return false;
        }

        if (bean.getCurrency() == null) {
            return false;
        }

        if (bean.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        if (bean.getDebtorIban() == null) {
            return false;
        }

        return bean.getCreditorIban() != null;
    }
}
