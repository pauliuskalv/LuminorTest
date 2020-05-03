package lt.pauliusk.luminor.bean.log;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity(name = "payment_notification_status")
public class PaymentNotificationStatus extends DatabaseEntity {
    private String code;
}
