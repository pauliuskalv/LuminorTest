package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.PaymentStatus;
import lt.pauliusk.luminor.dao.payment.PaymentStatusDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusConverter implements BeanConverter<PaymentStatus, PaymentStatusDTO> {
    private final PaymentStatusDAO paymentStatusDAO;

    public PaymentStatusConverter(@Autowired PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

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
        return paymentStatusDAO.findPaymentStatusByCode(dto.getCode()).orElseThrow();
    }
}
