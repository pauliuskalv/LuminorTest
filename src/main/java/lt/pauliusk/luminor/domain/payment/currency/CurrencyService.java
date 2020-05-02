package lt.pauliusk.luminor.domain.payment.currency;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.constant.CurrencyConst;
import lt.pauliusk.luminor.dao.payment.CurrencyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyDAO currencyDAO;

    public CurrencyService(@Autowired CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public Currency getCurrencyByCode(CurrencyConst currency) throws Exception {
        return currencyDAO.findCurrencyByCode(currency.getCode()).orElseThrow(() -> new Exception("No such currency with code " + currency.getCode()));
    }
}
