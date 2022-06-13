package jac.fsd02.foodorder.repository;

import jac.fsd02.foodorder.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCategoryId(Long categoryId);

}
