package jac.fsd02.foodorder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.*;
import jac.fsd02.foodorder.repository.CartRepository;
import jac.fsd02.foodorder.repository.OrderDetailRepository;
import jac.fsd02.foodorder.repository.OrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    SessionService sessionService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRespository orderRespository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    private ObjectMapper mapper;

    public OrderService(OrderRespository orderRespository, ObjectMapper mapper) {
        this.orderRespository = orderRespository;
        this.mapper = mapper;
    }

    public Order createOrder(CartListForm cartListForm) {
        List<Cart> cartList = cartListForm.getCartList();

        //insert into tbl_order
        Order order = new Order();
        order.setUserId(sessionService.getUserIdFromSession());
        order.setItemTotalPrice(cartListForm.getItemTotalPrice());
        order.setTax(cartListForm.getTax());
        order.setShippingFee(cartListForm.getShippingFee());
        order.setOrderTotalPrice(cartListForm.getOrderTotalPrice());
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.UNPAID);
        Order savedOrder = orderRespository.save(order);

        //insert into tbl_order_detail
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(savedOrder.getId());
            orderDetail.setItemId(cart.getItemId());
            orderDetail.setPrice(cart.getItemPrice());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetailList.add(orderDetail);
        }
        orderDetailRepository.saveAll(orderDetailList);

        //delete from cart
        for (Cart cart : cartList) {
            cartRepository.deleteById(cart.getId());
        }

        return savedOrder;
    }

    public Order updatePaymentInfo(Long orderId, Long paymentId) throws RecordNotFoundException {
        Optional<Order> optOrder = orderRespository.findById(orderId);
        if (optOrder.isPresent()){
            Order order = optOrder.get();
            order.setOrderStatus(OrderStatus.PAID);
            order.setPaymentId(paymentId);
            return orderRespository.save(order);
        }
        throw new RecordNotFoundException("Order info not found");
    }

    public List<Order> getOrderList() {
        return (List<Order>) orderRespository.findAll();
    }

    public Order getOrderById(long id) throws RecordNotFoundException {
        Optional<Order> orderInDb =  orderRespository.findById(id);
        if(orderInDb.isPresent()){
            return orderInDb.get();
        }
        else{
            throw new RecordNotFoundException("There is no Order");
        }
    }

    public Order saveOrUpdateOrder(Order order) {
        return orderRespository.save(order);
    }

    public void deleteOrder(long id) {
        orderRespository.deleteById(id);
    }
}
