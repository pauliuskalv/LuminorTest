package lt.pauliusk.luminor.rest.dto.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaymentDTO extends BaseDTO {
    private BigDecimal amount;

    private CurrencyDTO currency;

    private String details;
    private String debtorIban;
    private String creditorIban;

    private BigDecimal cancellationFee;

    private String bic;
}
