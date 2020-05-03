package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.PaymentType;
import lt.pauliusk.luminor.dao.payment.PaymentTypeDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeConverter implements BeanConverter<PaymentType, PaymentTypeDTO> {
    private final PaymentTypeDAO paymentTypeDAO;

    public PaymentTypeConverter(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    @Override
    public PaymentTypeDTO convertBeanToDTO(PaymentType bean) {
        PaymentTypeDTO dto = new PaymentTypeDTO();

        dto.setId(bean.getId());
        dto.setName(bean.getName());
        dto.setCode(bean.getCode());

        return dto;
    }

    @Override
    public PaymentType convertDTOToBean(PaymentTypeDTO dto) {
        return paymentTypeDAO.findPaymentTypeByCode(dto.getCode()).orElseThrow();
    }
}
