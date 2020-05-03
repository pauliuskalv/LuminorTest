package lt.pauliusk.luminor.rest.dto.bean;

import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

@Getter
@Setter
public class PaymentStatusDTO extends BaseDTO {
    private String name;
    private String code;
}
