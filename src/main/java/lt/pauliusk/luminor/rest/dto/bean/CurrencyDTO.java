package lt.pauliusk.luminor.rest.dto.bean;

import lombok.Getter;
import lombok.Setter;
import lt.pauliusk.luminor.rest.dto.BaseDTO;

@Getter
@Setter
public class CurrencyDTO extends BaseDTO {
    private String name;
    private String code;
}
