package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.dao.payment.PaymentDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentConverterWithId implements BeanConverter<Payment, PaymentWithIdDTO> {
    private final PaymentDAO paymentDAO;

    public PaymentConverterWithId(@Autowired PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public PaymentWithIdDTO convertBeanToDTO(Payment bean) {
        PaymentWithIdDTO dto = new PaymentWithIdDTO();

        dto.setId(bean.getId());

        return dto;
    }

    @Override
    public Payment convertDTOToBean(PaymentWithIdDTO dto) {
        return paymentDAO.findById(dto.getId()).orElseThrow();
    }
}
