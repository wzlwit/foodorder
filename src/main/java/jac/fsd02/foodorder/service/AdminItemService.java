package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Item;
import jac.fsd02.foodorder.repository.AdminItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AdminItemService {
    @Autowired
    AdminItemRepository repository;

    public List<Item> getItemList() {
        return (List<Item>) repository.findAll();
    }

    public Item getItemById(long id) throws RecordNotFoundException {
        Optional<Item> itemInDb =  repository.findById(id);
        if(itemInDb.isPresent()){
            return itemInDb.get();
        }
        else{
            throw new RecordNotFoundException("There is no Item");
        }
    }

    public Item saveOrUpdateItem(Item item) {
        return repository.save(item);
    }


    public void deleteItem(long id) {
        repository.deleteById(id);
    }
}
