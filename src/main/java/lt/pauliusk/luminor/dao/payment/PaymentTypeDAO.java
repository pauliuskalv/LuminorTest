package lt.pauliusk.luminor.dao.payment;

import lt.pauliusk.luminor.bean.payment.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTypeDAO extends CrudRepository<PaymentType, Long> {
    Optional<PaymentType> findPaymentTypeByCode(String code);
}
