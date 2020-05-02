package lt.pauliusk.luminor.dao.payment;

import lt.pauliusk.luminor.bean.payment.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyDAO extends CrudRepository<Currency, Long> {
    Optional<Currency> findCurrencyByCode(String code);
}
