package lt.pauliusk.luminor.bean.payment;

import lombok.Data;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "payment_status")
@Data
public class PaymentStatus extends DatabaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;
}
