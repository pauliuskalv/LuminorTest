package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentFee;
import lt.pauliusk.luminor.dao.payment.PaymentDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdCancellationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentConverterWithIdCancellation implements BeanConverter<Payment, PaymentWithIdCancellationDTO> {
    private final PaymentDAO paymentDAO;

    public PaymentConverterWithIdCancellation(@Autowired PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public PaymentWithIdCancellationDTO convertBeanToDTO(Payment bean) {
        PaymentWithIdCancellationDTO dto = new PaymentWithIdCancellationDTO();

        dto.setId(bean.getId());

        BigDecimal cancellationFee = BigDecimal.ZERO;
        for (PaymentFee fee : bean.getPaymentFees()) {
            cancellationFee = cancellationFee.add(fee.getAmount());
        }
        dto.setCancellationFee(cancellationFee);

        return dto;
    }

    @Override
    public Payment convertDTOToBean(PaymentWithIdCancellationDTO dto) {
        return paymentDAO.findById(dto.getId()).orElseThrow();
    }
}
