package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentStatusDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusConverter implements BeanConverter<PaymentStatus, PaymentStatusDTO> {
    @Override
    public PaymentStatusDTO convertBeanToDTO(PaymentStatus bean) {
        PaymentStatusDTO dto = new PaymentStatusDTO();

        dto.setId(bean.getId());
        dto.setName(bean.getName());
        dto.setCode(bean.getCode());

        return dto;
    }

    @Override
    public PaymentStatus convertDTOToBean(PaymentStatusDTO dto) {
        PaymentStatus paymentStatus = new PaymentStatus();

        paymentStatus.setId(dto.getId());
        paymentStatus.setName(dto.getName());
        paymentStatus.setCode(dto.getCode());

        return paymentStatus;
    }
}
