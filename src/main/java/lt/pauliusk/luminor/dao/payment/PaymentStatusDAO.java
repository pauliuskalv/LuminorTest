package lt.pauliusk.luminor.dao.payment;

import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusDAO extends CrudRepository<PaymentStatus, Long> {
    Optional<PaymentStatus> findPaymentStatusByCode(String code);
}
