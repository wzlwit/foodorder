package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Category;
import jac.fsd02.foodorder.repository.AdminCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCategoryService {
    @Autowired
    AdminCategoryRepository repository;

    public List<Category> getCategoryList() {
        return (List<Category>) repository.findAll();
    }

    public Category getCategoryById(long id) throws RecordNotFoundException {
        Optional<Category> categoryInDb =  repository.findById(id);
        if(categoryInDb.isPresent()){
            return categoryInDb.get();
        }
        else{
            throw new RecordNotFoundException("There is no category");
        }
    }

    public Category saveOrUpdateCategory(Category category) {
            return repository.save(category);
    }

    public void deleteCategory(long id) {
        repository.deleteById(id);
    }
}
