package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Payment;
import jac.fsd02.foodorder.service.OrderService;
import jac.fsd02.foodorder.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    OrderService orderService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @GetMapping("/addPayment")
//    public String paymentPage(Model model) {
//        //it will call the db/service and fetches the data
////        List<Payment> paymentList = paymentService.getPaymentList();
////        model.addAttribute("allPayments", paymentList);
//
//        Payment payment = new Payment();
//        model.addAttribute("payment", payment);
//
//        double cartTotal = cartService.getTotal();
//        model.addAttribute("cartTotal", cartTotal);
//
////        cartService.getUserId();
////        Cart cart = cartService.getuser;
////        model.addAttribute("userId", cart.userId);
//
////        double avg = paymentService.getAvg();
////        model.addAttribute("avggrade", avg);
//        return "payment";
//    }

    @PostMapping("/savePayment")
    public String savePayment
            (@Valid @ModelAttribute("payment") Payment paymentToSave, BindingResult result) {
        //This annotated method will allow access to the Student object in your View, since it gets automatically added to the Models by Spring.
        //
        //See "Using @ModelAttribute".

        //if I have validation issues
        if (result.hasErrors()) {
            //I need to stay in the current page !!!!
            return "payment";
        }

        Payment payment = paymentService.savePayment(paymentToSave);
        try{
            orderService.updatePaymentInfo(payment.getOrderId(), payment.getId());
        } catch (RecordNotFoundException e) {
            return "500";
        }


        return "paymentCompleted";
    }
}
