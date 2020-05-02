package lt.pauliusk.luminor.dao.payment;

import lt.pauliusk.luminor.bean.payment.PaymentFee;
import org.springframework.data.repository.CrudRepository;

public interface PaymentFeeDAO extends CrudRepository<PaymentFee, Long> {
}
