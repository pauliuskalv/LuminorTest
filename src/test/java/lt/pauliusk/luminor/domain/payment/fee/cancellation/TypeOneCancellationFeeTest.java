package lt.pauliusk.luminor.domain.payment.fee.cancellation;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentType;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TypeOneCancellationFeeTest {
    @InjectMocks
    private TypeOneCancellationFee typeOneCancellationFee;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void calculate_whenAmount100_shouldReturn005() {
        Payment payment = new Payment();

        payment.setCreateDate(ZonedDateTime.now().minusHours(1));
        payment.setAmount(new BigDecimal("100"));

        assertEquals(typeOneCancellationFee.calculate(payment), new BigDecimal("0.05"));
    }

    @Test
    void isApplicable_whenPaymentTypeOne_shouldReturnTrue() {
        Payment payment = new Payment();

        PaymentType type = mock(PaymentType.class);
        when(type.getCode()).thenReturn(PaymentTypeConst.TYPE_ONE.getCode());
        payment.setPaymentType(type);

        assertTrue(typeOneCancellationFee.isApplicable(payment));
    }

    @Test
    void isApplicable_whenPaymentTypeTwo_shouldReturnFalse() {
        Payment payment = new Payment();

        PaymentType type = mock(PaymentType.class);
        when(type.getCode()).thenReturn(PaymentTypeConst.TYPE_TWO.getCode());
        payment.setPaymentType(type);

        assertFalse(typeOneCancellationFee.isApplicable(payment));
    }
}