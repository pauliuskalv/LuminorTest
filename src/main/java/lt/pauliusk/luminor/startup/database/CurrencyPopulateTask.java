package lt.pauliusk.luminor.startup.database;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.dao.payment.CurrencyDAO;
import lt.pauliusk.luminor.startup.DatabasePopulateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Populates the database with EUR and USD currencies
 */

@Component
public class CurrencyPopulateTask implements DatabasePopulateTask {
    private final CurrencyDAO currencyDAO;

    public CurrencyPopulateTask(@Autowired CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public void afterPropertiesSet() {
        Currency currencyUsd = new Currency();
        currencyUsd.setCode("USD");
        currencyUsd.setName("United States Dollar");

        Currency currencyEur = new Currency();
        currencyEur.setCode("EUR");
        currencyEur.setName("Euro");

        currencyDAO.save(currencyUsd);
        currencyDAO.save(currencyEur);
    }
}
