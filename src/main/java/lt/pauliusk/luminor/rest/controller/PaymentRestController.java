package lt.pauliusk.luminor.rest.controller;

import lt.pauliusk.luminor.domain.payment.PaymentService;
import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdCancellationDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdDTO;
import lt.pauliusk.luminor.rest.integration.geolocation.GeolocationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {
    private final Logger logger = Logger.getLogger(PaymentRestController.class.getName());

    private final PaymentService paymentService;
    private final GeolocationRequestService geolocationRequestService;

    public PaymentRestController(
            @Autowired PaymentService paymentService,
            @Autowired GeolocationRequestService geolocationRequestService
    ) {
        this.paymentService = paymentService;
        this.geolocationRequestService = geolocationRequestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentWithIdCancellationDTO> getPayment(@PathVariable("id") Long id, ServerHttpRequest request) {
        try {
            doGeolocationRequest("getPayment()", request);

            return ResponseEntity.ok(
                    paymentService.findPaymentById(id)
            );
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occurred while trying to find payment with id " + id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/uncancelled")
    public ResponseEntity<List<PaymentWithIdDTO>> getUncancelledPayments(@RequestParam(name = "amount", required = false) BigDecimal amount, ServerHttpRequest request) {
        try {
            doGeolocationRequest("getUncancelledPayments()", request);

            return ResponseEntity.ok(paymentService.findUncancelledPayments(amount));
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occurred while querying uncancelled payments", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO payment, ServerHttpRequest request) {
        try {
            doGeolocationRequest("createPayment()", request);

            return ResponseEntity.ok(
                    paymentService.createPayment(payment)
            );
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occurred while trying to create payment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<PaymentDTO> cancelPayment(@PathVariable("id") Long id, ServerHttpRequest request) {
        try {
            doGeolocationRequest("cancelPayment()", request);

            return ResponseEntity.ok(
                    paymentService.cancelPayment(id)
            );
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occurred while trying to cancel payment with id " + id, e);
            return ResponseEntity.badRequest().build();
        }
    }

    private void doGeolocationRequest(String methodName, ServerHttpRequest recordedAddress) {
        geolocationRequestService.getCountryAsync(recordedAddress.getRemoteAddress().getHostString(), (rs) -> {
            logger.log(Level.INFO, methodName + ": " + rs);
        });
    }
}
