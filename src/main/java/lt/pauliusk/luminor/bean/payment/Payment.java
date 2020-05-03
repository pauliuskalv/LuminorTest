package lt.pauliusk.luminor.bean.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity(name = "payment")
public class Payment extends DatabaseEntity {
    @Column(name = "amount", nullable = false)
    @Positive(message = "Payment amount must be positive!")
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency")
    private Currency currency;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status")
    private PaymentStatus paymentStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type")
    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PaymentFee> paymentFees = new LinkedList<>();

    @Column(name = "details", length = 500)
    private String details;

    @Column(name = "debtor_iban", length = 500)
    private String debtorIban;

    @Column(name = "creditor_iban", length = 500)
    private String creditorIban;

    @Column(name = "bic")
    private String bic;

    @Column(name = "create_date")
    private ZonedDateTime createDate;
}
