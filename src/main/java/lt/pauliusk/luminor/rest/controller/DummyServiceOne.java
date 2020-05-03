package lt.pauliusk.luminor.rest.controller;

import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Random;

@RestController
@RequestMapping("/dummyserviceone/notify")
public class DummyServiceOne {
    @PostMapping
    public ResponseEntity<PaymentDTO> notify(@RequestBody PaymentDTO payment) {
        Random random = new Random(Calendar.getInstance().getTimeInMillis());

        if (random.nextBoolean()) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
