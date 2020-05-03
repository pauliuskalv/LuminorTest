package lt.pauliusk.luminor.rest.dto.bean;

import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentWithIdCancellationDTO extends BaseDTO {
    private BigDecimal cancellationFee;
}
