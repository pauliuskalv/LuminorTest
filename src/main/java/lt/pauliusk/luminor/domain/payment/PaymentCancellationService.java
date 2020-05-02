package lt.pauliusk.luminor.domain.payment;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.constant.CurrencyConst;
import lt.pauliusk.luminor.bean.payment.constant.PaymentStatusConst;
import lt.pauliusk.luminor.dao.payment.PaymentDAO;
import lt.pauliusk.luminor.domain.payment.currency.CurrencyService;
import lt.pauliusk.luminor.domain.payment.fee.CancellationFee;
import lt.pauliusk.luminor.domain.payment.fee.PaymentFeeFactory;
import lt.pauliusk.luminor.domain.payment.status.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PaymentCancellationService {
    private final PaymentDAO paymentDAO;
    private final List<CancellationFee> cancellationFees;
    private final PaymentStatusService paymentStatusService;
    private final CurrencyService currencyService;
    private final PaymentFeeFactory paymentFeeFactory;

    public PaymentCancellationService(
            @Autowired PaymentDAO paymentDAO,
            @Autowired List<CancellationFee> cancellationFees,
            @Autowired PaymentStatusService paymentStatusService,
            @Autowired CurrencyService currencyService,
            @Autowired PaymentFeeFactory paymentFeeFactory
    ) {
        this.paymentDAO = paymentDAO;
        this.cancellationFees = cancellationFees;
        this.paymentStatusService = paymentStatusService;
        this.currencyService = currencyService;
        this.paymentFeeFactory = paymentFeeFactory;
    }

    @Transactional
    public Payment cancelPayment(Payment payment) throws Exception {
        if (!canCancelPayment(payment)) {
            throw new Exception("Cant cancel this payment!");
        }

        for (CancellationFee cancellationFee : cancellationFees) {
            if (cancellationFee.isApplicable(payment)) {
                payment.getPaymentFees().add(
                        paymentFeeFactory.create(
                                cancellationFee.calculate(payment),
                                currencyService.getCurrencyByCode(CurrencyConst.EUR),
                                payment
                        )
                );
            }
        }

        payment.setPaymentStatus(paymentStatusService.getPaymentStatusByCode(PaymentStatusConst.CANCELLED));
        payment = paymentDAO.save(payment);

        return payment;
    }

    private boolean canCancelPayment(Payment payment) {
        // Check if payment is already cancelled
        if (payment.getPaymentStatus().getCode().equals(PaymentStatusConst.CANCELLED.getCode())) {
            return false;
        }

        ZonedDateTime currDate = ZonedDateTime.now();
        int dayOfYear = currDate.getDayOfYear();
        int year = currDate.getYear();

        int paymentDayOfYear = payment.getCreateDate().getDayOfYear();
        int paymentYear = payment.getCreateDate().getYear();

        return dayOfYear == paymentDayOfYear && year == paymentYear;
    }
}
