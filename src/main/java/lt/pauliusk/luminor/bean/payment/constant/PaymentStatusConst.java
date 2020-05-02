package lt.pauliusk.luminor.bean.payment.constant;

import lombok.Getter;

@Getter
public enum PaymentStatusConst {
    NEW("NEW"),
    PROCESSED("PROCESSED"),
    CANCELLED("CANCELLED");

    private final String code;

    PaymentStatusConst(String code) {
        this.code = code;
    }
}
