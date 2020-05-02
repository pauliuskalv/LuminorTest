package lt.pauliusk.luminor.bean.payment.constant;

import lombok.Getter;

@Getter
public enum PaymentTypeConst {
    TYPE_ONE("ONE"),
    TYPE_TWO("TWO"),
    TYPE_THREE("THREE");

    private final String code;

    PaymentTypeConst(String code) {
        this.code = code;
    }
}
