package lt.pauliusk.luminor.bean.log.constant;

import lombok.Getter;

@Getter
public enum PaymentNotificationStatusConst {
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private final String code;

    PaymentNotificationStatusConst(String code) {
        this.code = code;
    }
}
