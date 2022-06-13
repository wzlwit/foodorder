package jac.fsd02.foodorder.repository;

import jac.fsd02.foodorder.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryRepository extends CrudRepository<Category,Long> {
}
