package jac.fsd02.foodorder.repository;

import jac.fsd02.foodorder.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCityRepository extends CrudRepository<City,Long> {
}
