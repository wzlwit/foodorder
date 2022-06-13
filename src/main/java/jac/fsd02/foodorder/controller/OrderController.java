package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.CartListForm;
import jac.fsd02.foodorder.model.Order;
import jac.fsd02.foodorder.model.Payment;
import jac.fsd02.foodorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/order")
    public String homePage(Model model){
        //it will call the db/service and fetches the data
        List<Order> orderList = orderService.getOrderList();
        model.addAttribute("allOrder",orderList);
        return "orders";
    }

    @GetMapping("/addNewOrder")
    public String addNewOrder(Model model){
//        model.addAttribute("userCity", adminCityService.getCityList());
        Order order = new Order();

        model.addAttribute("order", order);
        return "addOrUpdate-order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@Valid @ModelAttribute("order") Order addOrder, BindingResult result){
        //if I have validation issues
        if (result.hasErrors()){
            //I need to stay in the current page !!!!
            return "addOrUpdate-order";
        }
        orderService.saveOrUpdateOrder(addOrder);

        return "redirect:/order";
    }
    @GetMapping("/showFormForUpdateOrder/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model)throws RecordNotFoundException {
        //now model needs to be populated with the information that comes from db
        //you have your id-> so you can fetch information from database
//        model.addAttribute("userCity", adminCityService.getCityList());
        Order orderFromDb = orderService.getOrderById(id);
        model.addAttribute("order", orderFromDb);
        return "addOrUpdate-order";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){
        //here we call db/service to delete the object
        orderService.deleteOrder(id);
//        return "orders";
        return "redirect:/order";
    }

    //add of
    @PostMapping("/checkout")
    public String createOrder(@ModelAttribute("cartListForm") CartListForm cartListForm, Model model){
//        cartList = (ArrayList<Cart>)model.getAttribute("result");
        System.out.println(cartListForm.toString());
        Order order = orderService.createOrder(cartListForm);

        Payment payment = new Payment();
        payment.setOrderId(order.getId());

        model.addAttribute("payment", payment);
        model.addAttribute("orderTotalPrice", cartListForm.getOrderTotalPrice());
        return "payment";
    }




}
