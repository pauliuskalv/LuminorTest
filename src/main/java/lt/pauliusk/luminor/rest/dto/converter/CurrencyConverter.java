package lt.pauliusk.luminor.rest.dto.converter;

import lt.pauliusk.luminor.bean.payment.Currency;
import lt.pauliusk.luminor.dao.payment.CurrencyDAO;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter implements BeanConverter<Currency, CurrencyDTO> {
    private final CurrencyDAO currencyDAO;

    public CurrencyConverter(@Autowired CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public CurrencyDTO convertBeanToDTO(Currency bean) {
        CurrencyDTO dto = new CurrencyDTO();

        dto.setId(bean.getId());
        dto.setName(bean.getName());
        dto.setCode(bean.getCode());

        return dto;
    }

    @Override
    public Currency convertDTOToBean(CurrencyDTO dto) {
        return currencyDAO.findCurrencyByCode(dto.getCode()).orElseThrow();
    }
}
