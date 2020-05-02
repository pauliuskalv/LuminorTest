package lt.pauliusk.luminor.rest.dto.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CurrencyDTO extends BaseDTO {
    private String name;
    private String code;
}
