package lt.pauliusk.luminor.bean.payment;

import lombok.Data;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "payment_fee")
public class PaymentFee extends DatabaseEntity {
    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "currency")
    private Currency currency;
}
