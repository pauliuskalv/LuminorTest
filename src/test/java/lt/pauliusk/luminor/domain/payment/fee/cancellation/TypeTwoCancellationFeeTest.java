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

class TypeTwoCancellationFeeTest {
    @InjectMocks
    private TypeTwoCancellationFee typeTwoCancellationFee;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void calculate_whenAmount100_shouldReturn01() {
        Payment payment = new Payment();

        payment.setCreateDate(ZonedDateTime.now().minusHours(1));
        payment.setAmount(new BigDecimal("100"));

        assertEquals(typeTwoCancellationFee.calculate(payment), new BigDecimal("0.1"));
    }

    @Test
    void isApplicable_whenPaymentTypeTwo_shouldReturnTrue() {
        Payment payment = new Payment();

        PaymentType type = mock(PaymentType.class);
        when(type.getCode()).thenReturn(PaymentTypeConst.TYPE_TWO.getCode());
        payment.setPaymentType(type);

        assertTrue(typeTwoCancellationFee.isApplicable(payment));
    }

    @Test
    void isApplicable_whenPaymentTypeOne_shouldReturnFalse() {
        Payment payment = new Payment();

        PaymentType type = mock(PaymentType.class);
        when(type.getCode()).thenReturn(PaymentTypeConst.TYPE_ONE.getCode());
        payment.setPaymentType(type);

        assertFalse(typeTwoCancellationFee.isApplicable(payment));
    }
}