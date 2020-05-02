package lt.pauliusk.luminor.rest.dto.bean;

import lombok.Data;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

@Data
public class PaymentTypeDTO extends BaseDTO {
    private String name;
    private String code;
}
