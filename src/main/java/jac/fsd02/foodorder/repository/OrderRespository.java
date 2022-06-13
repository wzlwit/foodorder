package jac.fsd02.foodorder.repository;

import jac.fsd02.foodorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order,Long> {

}


