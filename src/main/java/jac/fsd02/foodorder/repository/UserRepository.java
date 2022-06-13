package jac.fsd02.foodorder.repository;

import jac.fsd02.foodorder.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    public User findByName(String userName);
}
