package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Payment;
import jac.fsd02.foodorder.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;


    public List<Payment> getPaymentList() {
        return (List<Payment>) paymentRepository.findAll();
    }

//    public double getAvg() {
//        List<Payment> paymentList = getPaymentList();
//        double sum = 0;
//        double result = 0;
//        for (int i = 0; i < paymentList.size(); i++) {
//            sum = sum + paymentList.get(i).getId();
//        }
//
//        if (paymentList.size() == 0) {
//            result = 0;
//        } else result = (double) (sum / paymentList.size());
//
//        return result;
//
//    }

    public Payment getPaymentById(long id) throws RecordNotFoundException {
        Optional<Payment> paym = paymentRepository.findById(id);
        if (paym.isPresent()) {
            return paym.get();
        } else {
            throw new RecordNotFoundException("There is no student");
        }
    }

    public Payment savePayment(Payment payment) {

        return paymentRepository.save(payment);
    }


    public void deletePayment(long id) {
        paymentRepository.deleteById(id);
    }
}
