package lt.pauliusk.luminor.dao.payment;

import lt.pauliusk.luminor.bean.payment.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentDAO extends CrudRepository<Payment, Long> {
    @Query("SELECT p " +
            "FROM payment p " +
            "LEFT JOIN p.paymentStatus ps " +
            "WHERE ps.code != 'CANCELLED' AND (p.amount = :amount OR :amount IS NULL)")
    List<Payment> findUncancelledPayments(BigDecimal amount);
}
