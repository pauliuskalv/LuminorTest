package lt.pauliusk.luminor.rest.controller;

import lt.pauliusk.luminor.rest.dto.bean.CurrencyDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentRestControllerTest {
    private static String URL = "/api/payment";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void createPayment() {
        BigDecimal paymentAmount = BigDecimal.valueOf(10);
        String debtorIban = "YeYeYe";
        String creditorIban = "NoNoNo";

        PaymentDTO request = new PaymentDTO();
        request.setAmount(paymentAmount);
        request.setDebtorIban(debtorIban);
        request.setCreditorIban(creditorIban);

        CurrencyDTO currency = new CurrencyDTO();
        currency.setCode("EUR");

        request.setCurrency(currency);
        request.setDetails("deets");
        request.setBic("YES");

        ResponseEntity<PaymentDTO> response = restTemplate.postForEntity(URL, request, PaymentDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        PaymentDTO payment = response.getBody();

        assertNotNull(payment.getId());
    }
}