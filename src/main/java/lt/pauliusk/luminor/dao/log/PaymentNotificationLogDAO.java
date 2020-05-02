package lt.pauliusk.luminor.dao.log;

import lt.pauliusk.luminor.bean.log.PaymentNotificationLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentNotificationLogDAO extends CrudRepository<PaymentNotificationLog, Long> {
}
