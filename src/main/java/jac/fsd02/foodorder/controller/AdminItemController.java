package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.Item;
import jac.fsd02.foodorder.service.AdminCategoryService;
import jac.fsd02.foodorder.service.AdminItemService;
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
public class AdminItemController {
    final AdminItemService adminItemService;
    final AdminCategoryService adminCategoryService;

    public AdminItemController(AdminItemService adminItemService, AdminCategoryService adminCategoryService){
        this.adminItemService = adminItemService;
        this.adminCategoryService = adminCategoryService;
    }
    @GetMapping("/itemList")
    public String homePage(Model model){
        //it will call the db/service and fetches the data
        List<Item> itemList = adminItemService.getItemList();
        model.addAttribute("allItems",itemList);
        return "itemList";
    }

    @GetMapping("/addNewItem")
    public String addNewCategory(Model model){
        model.addAttribute("itemCategory",adminCategoryService.getCategoryList());
        Item item = new Item();
        model.addAttribute("item", item);
        return "addOrUpdate-item";
    }

    @PostMapping("/saveItem")
    public String saveItem(@Valid @ModelAttribute("item") Item addItem, BindingResult result){
        //if I have validation issues
        if (result.hasErrors()){
            //I need to stay in the current page !!!!
            return "addOrUpdate-item";
        }
        adminItemService.saveOrUpdateItem(addItem);

        return "redirect:/itemList";
    }
    @GetMapping("/showFormForUpdateItem/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model)throws RecordNotFoundException {
        //now model needs to be populated with the information that comes from db
        //you have your id-> so you can fetch information from database
        model.addAttribute("itemCategory",adminCategoryService.getCategoryList());
        Item itemFromDb = adminItemService.getItemById(id);
        model.addAttribute("item", itemFromDb);
        return "addOrUpdate-item";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){
        //here we call db/service to delete the object
        adminItemService.deleteItem(id);
        return "redirect:/itemList";
    }
}
