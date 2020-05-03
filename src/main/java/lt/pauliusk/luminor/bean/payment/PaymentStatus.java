package lt.pauliusk.luminor.bean.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity(name = "payment_status")
public class PaymentStatus extends DatabaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;
}
