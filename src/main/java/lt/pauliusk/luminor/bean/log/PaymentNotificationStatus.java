package lt.pauliusk.luminor.bean.log;

import lombok.Data;
import lt.pauliusk.luminor.bean.DatabaseEntity;

import javax.persistence.Entity;

@Data
@Entity(name = "payment_notification_status")
public class PaymentNotificationStatus extends DatabaseEntity {
    private String code;
}
