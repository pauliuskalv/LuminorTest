package lt.pauliusk.luminor.bean.log;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.bean.DatabaseEntity;
import lt.pauliusk.luminor.bean.payment.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity(name = "payment_notification_log")
public class PaymentNotificationLog extends DatabaseEntity {
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "status")
    private PaymentNotificationStatus notificationStatus;

    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;

    @Column(name = "date")
    private ZonedDateTime date;
}
