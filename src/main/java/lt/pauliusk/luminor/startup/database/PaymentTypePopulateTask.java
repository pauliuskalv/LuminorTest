package lt.pauliusk.luminor.startup.database;

import lt.pauliusk.luminor.bean.payment.PaymentType;
import lt.pauliusk.luminor.bean.payment.constant.PaymentTypeConst;
import lt.pauliusk.luminor.dao.payment.PaymentTypeDAO;
import lt.pauliusk.luminor.startup.DatabasePopulateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypePopulateTask implements DatabasePopulateTask {
    private final PaymentTypeDAO paymentTypeDAO;

    public PaymentTypePopulateTask(@Autowired PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    @Override
    public void afterPropertiesSet() {
        PaymentType paymentTypeOne = new PaymentType();
        paymentTypeOne.setName("Type one");
        paymentTypeOne.setCode(PaymentTypeConst.TYPE_ONE.getCode());

        PaymentType paymentTypeTwo = new PaymentType();
        paymentTypeTwo.setName("Type two");
        paymentTypeTwo.setCode(PaymentTypeConst.TYPE_TWO.getCode());

        PaymentType paymentTypeThree = new PaymentType();
        paymentTypeThree.setName("Type three");
        paymentTypeThree.setCode(PaymentTypeConst.TYPE_THREE.getCode());

        paymentTypeDAO.save(paymentTypeOne);
        paymentTypeDAO.save(paymentTypeTwo);
        paymentTypeDAO.save(paymentTypeThree);
    }
}
