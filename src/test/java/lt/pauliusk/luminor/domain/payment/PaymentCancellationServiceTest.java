package lt.pauliusk.luminor.domain.payment;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import lt.pauliusk.luminor.bean.payment.constant.PaymentStatusConst;
import lt.pauliusk.luminor.dao.payment.PaymentDAO;
import lt.pauliusk.luminor.domain.payment.currency.CurrencyService;
import lt.pauliusk.luminor.domain.payment.fee.CancellationFee;
import lt.pauliusk.luminor.domain.payment.fee.PaymentFeeFactory;
import lt.pauliusk.luminor.domain.payment.status.PaymentStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PaymentCancellationServiceTest {
    @InjectMocks
    PaymentCancellationService service;

    @Mock
    PaymentDAO paymentDAO;

    @Mock
    List<CancellationFee> cancellationFees;

    @Mock
    PaymentStatusService paymentStatusService;

    @Mock
    CurrencyService currencyService;

    @Mock
    PaymentFeeFactory paymentFeeFactory;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void cancelPayment_whenStatusCancelled_thenShouldNotCancel() {
        Payment payment = new Payment();

        PaymentStatus status = mock(PaymentStatus.class);
        when(status.getCode()).thenReturn(PaymentStatusConst.CANCELLED.getCode());
        payment.setPaymentStatus(status);

        assertThrows(Exception.class, () -> service.cancelPayment(payment), "Cant cancel this payment!");
    }

    @Test
    void cancelPayment_whenStatusNewAndPaymentOld_thenShouldNotCancel() {
        Payment payment = new Payment();

        PaymentStatus status = mock(PaymentStatus.class);
        when(status.getCode()).thenReturn(PaymentStatusConst.NEW.getCode());
        payment.setPaymentStatus(status);

        payment.setCreateDate(ZonedDateTime.now().minusDays(1));

        assertThrows(Exception.class, () -> service.cancelPayment(payment), "Cant cancel this payment!");
    }

    @Test
    void cancelPayment_whenStatusNewAndPaymentNew_thenShouldCancel() {
        Payment payment = new Payment();

        PaymentStatus status = mock(PaymentStatus.class);
        when(status.getCode()).thenReturn(PaymentStatusConst.NEW.getCode());
        payment.setPaymentStatus(status);

        when(cancellationFees.iterator()).thenReturn(mock(Iterator.class));

        payment.setCreateDate(ZonedDateTime.now());

        assertDoesNotThrow(() -> service.cancelPayment(payment));
    }
}