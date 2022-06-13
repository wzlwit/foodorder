package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long categoryId);
    List<Category> getCategoryList();


}
