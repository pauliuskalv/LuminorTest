package lt.pauliusk.luminor.dao.log;

import lt.pauliusk.luminor.bean.log.PaymentNotificationStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentNotificationStatusDAO extends CrudRepository<PaymentNotificationStatus, Long> {
    Optional<PaymentNotificationStatus> findPaymentNotificationStatusByCode(String code);
}
