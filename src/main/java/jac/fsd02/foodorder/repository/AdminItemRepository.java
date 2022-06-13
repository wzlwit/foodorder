package jac.fsd02.foodorder.repository;


import jac.fsd02.foodorder.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminItemRepository extends CrudRepository<Item,Long> {
}
