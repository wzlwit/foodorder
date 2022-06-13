package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.City;
import jac.fsd02.foodorder.model.User;
import jac.fsd02.foodorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> getUserList() {
        return (List<User>) repository.findAll();
    }

    public User getUserById(long id) throws RecordNotFoundException {
        Optional<User> userInDb =  repository.findById(id);
        if(userInDb.isPresent()){
            return userInDb.get();
        }
        else{
            throw new RecordNotFoundException("There is no City");
        }
    }

    public User saveOrUpdateUser(User user) {
        return repository.save(user);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}
