package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.PaymentFee;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.CurrencyDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentConverter implements BeanConverter<Payment, PaymentDTO> {
    private final BeanConverter<Currency, CurrencyDTO> currencyConverter;

    public PaymentConverter(@Autowired BeanConverter<Currency, CurrencyDTO> currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    @Override
    public PaymentDTO convertBeanToDTO(Payment bean) {
        PaymentDTO dto = new PaymentDTO();

        dto.setId(bean.getId());

        BigDecimal cancellationFee = BigDecimal.ZERO;
        for (PaymentFee fee : bean.getPaymentFees()) {
            cancellationFee = cancellationFee.add(fee.getAmount());
        }
        dto.setCancellationFee(cancellationFee);

        dto.setCurrency(currencyConverter.convertBeanToDTO(bean.getCurrency()));
        dto.setAmount(bean.getAmount());
        dto.setDetails(bean.getDetails());
        dto.setCreditorIban(bean.getCreditorIban());
        dto.setDebtorIban(bean.getDebtorIban());
        dto.setBic(bean.getBic());

        return dto;
    }

    @Override
    public Payment convertDTOToBean(PaymentDTO dto) {
        Payment bean = new Payment();

        bean.setId(dto.getId());

        if (dto.getCurrency() != null) {
            bean.setCurrency(currencyConverter.convertDTOToBean(dto.getCurrency()));
        }

        bean.setDebtorIban(dto.getDebtorIban());
        bean.setCreditorIban(dto.getCreditorIban());
        bean.setDetails(dto.getDetails());
        bean.setBic(dto.getBic());
        bean.setAmount(dto.getAmount());

        return bean;
    }
}
