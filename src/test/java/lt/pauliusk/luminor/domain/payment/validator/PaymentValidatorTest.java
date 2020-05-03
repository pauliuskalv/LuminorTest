package lt.pauliusk.luminor.domain.payment.validator;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

class PaymentValidatorTest {
    @InjectMocks
    private PaymentValidator validator;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void isValid_whenEmpty_shouldFail() {
        Payment payment = new Payment();

        assertFalse(validator.isValid(payment));
    }

    @Test
    void isValid_whenAmountNegativeHasCreditorDebtor_shouldFail() {
        Payment payment = new Payment();

        payment.setAmount(new BigDecimal("-100.00"));
        payment.setCreditorIban("");
        payment.setDebtorIban("");

        assertFalse(validator.isValid(payment));
    }

    @Test
    void isValid_whenAmountPositiveHasCreditorDebtor_shouldPass() {
        Payment payment = new Payment();

        payment.setAmount(new BigDecimal("100.00"));
        payment.setCreditorIban("");
        payment.setDebtorIban("");
        payment.setCurrency(mock(Currency.class));

        assertTrue(validator.isValid(payment));
    }
}