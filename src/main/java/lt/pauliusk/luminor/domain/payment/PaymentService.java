package lt.pauliusk.luminor.domain.payment;

import lt.pauliusk.luminor.bean.payment.Payment;
import lt.pauliusk.luminor.bean.payment.constant.PaymentStatusConst;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import lt.pauliusk.luminor.dao.payment.PaymentDAO;
import lt.pauliusk.luminor.domain.Validator;
import lt.pauliusk.luminor.domain.payment.status.PaymentStatusService;
import lt.pauliusk.luminor.domain.payment.type.PaymentTypeService;
import lt.pauliusk.luminor.rest.dto.BeanConverter;
import lt.pauliusk.luminor.rest.dto.bean.PaymentDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdCancellationDTO;
import lt.pauliusk.luminor.rest.dto.bean.PaymentWithIdDTO;
import lt.pauliusk.luminor.rest.integration.notification.PaymentNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final PaymentTypeService paymentTypeService;
    private final PaymentStatusService paymentStatusService;
    private final PaymentNotifyService paymentNotifyService;
    private final PaymentCancellationService paymentCancellationService;
    private final Validator<Payment> paymentValidator;

    private final BeanConverter<Payment, PaymentDTO> paymentConverter;
    private final BeanConverter<Payment, PaymentWithIdDTO> paymentWithIdConverter;
    private final BeanConverter<Payment, PaymentWithIdCancellationDTO> paymentWithIdCancellationConverter;

    public PaymentService(
            @Autowired PaymentDAO paymentDAO,
            @Autowired PaymentTypeService paymentTypeService,
            @Autowired PaymentStatusService paymentStatusService,
            @Autowired PaymentNotifyService paymentNotifyService,
            @Autowired PaymentCancellationService paymentCancellationService,
            @Autowired Validator<Payment> paymentValidator,

            @Autowired BeanConverter<Payment, PaymentDTO> paymentConverter,
            @Autowired BeanConverter<Payment, PaymentWithIdDTO> paymentWithIdConverter,
            @Autowired BeanConverter<Payment, PaymentWithIdCancellationDTO> paymentWithIdCancellationConverter
    ) {
        this.paymentDAO = paymentDAO;
        this.paymentTypeService = paymentTypeService;
        this.paymentStatusService = paymentStatusService;
        this.paymentNotifyService = paymentNotifyService;
        this.paymentCancellationService = paymentCancellationService;
        this.paymentValidator = paymentValidator;

        this.paymentConverter = paymentConverter;
        this.paymentWithIdConverter = paymentWithIdConverter;
        this.paymentWithIdCancellationConverter = paymentWithIdCancellationConverter;
    }

    @Transactional
    public PaymentDTO createPayment(PaymentDTO payment) throws Exception {
        Payment bean = paymentConverter.convertDTOToBean(payment);

        if (!paymentValidator.isValid(bean)) {
            throw new Exception("Payment is not valid!");
        }

        bean.setPaymentType(paymentTypeService.getRelevantType(bean));
        bean.setPaymentStatus(paymentStatusService.getPaymentStatusByCode(PaymentStatusConst.NEW));
        bean.setCreateDate(ZonedDateTime.now());

        bean = paymentDAO.save(bean);

        if (bean.getPaymentType().getCode().equals(PaymentTypeConst.TYPE_ONE.getCode())
                || bean.getPaymentType().getCode().equals(PaymentTypeConst.TYPE_TWO.getCode())) {
            paymentNotifyService.notifyAsync(bean);
        }

        return paymentConverter.convertBeanToDTO(bean);
    }

    @Transactional
    public PaymentWithIdCancellationDTO findPaymentById(Long id) throws Exception {
        Payment payment = paymentDAO.findById(id).orElseThrow(() -> new Exception("No such payment with id " + id));

        return paymentWithIdCancellationConverter.convertBeanToDTO(payment);
    }

    @Transactional
    public List<PaymentWithIdDTO> findUncancelledPayments(BigDecimal amount) {
        List<Payment> payments = paymentDAO.findUncancelledPayments(amount);

        List<PaymentWithIdDTO> paymentDTOs = new LinkedList<>();
        payments.forEach(payment -> paymentDTOs.add(paymentWithIdConverter.convertBeanToDTO(payment)));

        return paymentDTOs;
    }

    @Transactional
    public PaymentDTO cancelPayment(Long id) throws Exception {
        Payment payment = paymentDAO.findById(id).orElseThrow(() -> new Exception("No such payment with id " + id));

        payment = paymentCancellationService.cancelPayment(payment);

        return paymentConverter.convertBeanToDTO(payment);
    }
}
