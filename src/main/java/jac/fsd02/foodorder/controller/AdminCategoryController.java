package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Category;
import jac.fsd02.foodorder.service.AdminCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminCategoryController {
    final AdminCategoryService adminCategoryService;

    public AdminCategoryController(AdminCategoryService adminCategoryService){
        this.adminCategoryService = adminCategoryService;
    }
    @GetMapping("/catogoryList")
    public String homePage(Model model){
        //it will call the db/service and fetches the data
        List<Category> categoryList = adminCategoryService.getCategoryList();
        model.addAttribute("allCategory",categoryList);
        return "catogoryList";
//        return "homeadmin";
    }

    @GetMapping("/addNew")
    public String addNewCategory(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "addOrUpdate-category";
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category addCategory, BindingResult result){
        //if I have validation issues
        if (result.hasErrors()){
            //I need to stay in the current page !!!!
            return "addOrUpdate-category";
        }
        adminCategoryService.saveOrUpdateCategory(addCategory);

        return "redirect:/catogoryList";
    }

//    public String saveCategory( @ModelAttribute("category") Category addCategory){
//
//        adminCategoryService.saveOrUpdateCategory(addCategory);
//
//        return "redirect:/catogoryList";
//    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model)throws RecordNotFoundException {
        //now model needs to be populated with the information that comes from db
        //you have your id-> so you can fetch information from database
        Category categoryFromDb = adminCategoryService.getCategoryById(id);
        model.addAttribute("category", categoryFromDb);
        return "addOrUpdate-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){
        //here we call db/service to delete the object
        adminCategoryService.deleteCategory(id);
        return "redirect:/catogoryList";
    }
}
