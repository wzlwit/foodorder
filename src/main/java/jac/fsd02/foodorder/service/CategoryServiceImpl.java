package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Category;
import jac.fsd02.foodorder.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> optCategory = categoryRepository.findById(categoryId);

        Category category;
        if (optCategory.isPresent()) {
            category = optCategory.get();
        } else {
            category = new Category();
        }
        return category;
    }



    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
