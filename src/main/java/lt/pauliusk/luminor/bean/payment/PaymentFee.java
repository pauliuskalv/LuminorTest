package lt.pauliusk.luminor.bean.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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
