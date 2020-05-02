package lt.pauliusk.luminor.domain.payment.type;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentType;
import lt.pauliusk.luminor.bean.payment.constant.CurrencyConst;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import lt.pauliusk.luminor.dao.payment.PaymentTypeDAO;
import lt.pauliusk.luminor.domain.payment.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService {
    private final PaymentTypeDAO paymentTypeDAO;
    private final CurrencyService currencyService;

    public PaymentTypeService(
            @Autowired PaymentTypeDAO paymentTypeDAO,
            @Autowired CurrencyService currencyService
    ) {
        this.paymentTypeDAO = paymentTypeDAO;
        this.currencyService = currencyService;
    }

    public PaymentType getRelevantType(Payment payment) throws Exception {
        Currency currencyEur = currencyService.getCurrencyByCode(CurrencyConst.EUR);
        Currency currencyUsd = currencyService.getCurrencyByCode(CurrencyConst.USD);

        if ((payment.getCurrency().equals(currencyEur) || payment.getCurrency().equals(currencyUsd)) && payment.getBic() != null) {
            return paymentTypeDAO.findPaymentTypeByCode(PaymentTypeConst.TYPE_THREE.getCode()).orElseThrow();
        } else if (payment.getCurrency().equals(currencyEur) && payment.getDetails() != null) {
            return paymentTypeDAO.findPaymentTypeByCode(PaymentTypeConst.TYPE_ONE.getCode()).orElseThrow();
        } else if (payment.getCurrency().equals(currencyUsd)) {
            return paymentTypeDAO.findPaymentTypeByCode(PaymentTypeConst.TYPE_TWO.getCode()).orElseThrow();
        }

        throw new Exception("Payment does not correspond to any type!");
    }

    public PaymentType getPaymentType(PaymentTypeConst paymentType) throws Exception {
        return paymentTypeDAO.findPaymentTypeByCode(paymentType.getCode()).orElseThrow(() -> new Exception("No payment type with code " + paymentType.getCode()));
    }
}
