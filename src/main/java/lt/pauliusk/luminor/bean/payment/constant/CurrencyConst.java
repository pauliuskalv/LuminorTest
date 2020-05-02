package lt.pauliusk.luminor.bean.payment.constant;

import lombok.Getter;

@Getter
public enum CurrencyConst {
    USD("USD"),
    EUR("EUR");

    private final String code;

    CurrencyConst(String code) {
        this.code = code;
    }
}
