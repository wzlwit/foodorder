package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.City;
import jac.fsd02.foodorder.model.Item;
import jac.fsd02.foodorder.model.User;
import jac.fsd02.foodorder.service.AdminCityService;
import jac.fsd02.foodorder.service.UserService;
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
public class AdminUserController {
    final UserService userService;
    final AdminCityService adminCityService;

    public AdminUserController(UserService userService, AdminCityService adminCityService){
        this.userService = userService;
        this.adminCityService = adminCityService;
    }
    @GetMapping("/user")
    public String homePage(Model model){
        //it will call the db/service and fetches the data
        List<User> userList = userService.getUserList();
        model.addAttribute("allUser",userList);
        return "users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model){
        model.addAttribute("userCity", adminCityService.getCityList());
        User user = new User();

        model.addAttribute("user", user);
        return "addOrUpdate-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User addUser, BindingResult result){
        //if I have validation issues
        if (result.hasErrors()){
            //I need to stay in the current page !!!!
            return "addOrUpdate-user";
        }
        userService.saveOrUpdateUser(addUser);

        return "redirect:/user";
    }
    @GetMapping("/showFormForUpdateUser/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model)throws RecordNotFoundException {
        //now model needs to be populated with the information that comes from db
        //you have your id-> so you can fetch information from database
        model.addAttribute("userCity", adminCityService.getCityList());
        User userFromDb = userService.getUserById(id);
        model.addAttribute("user", userFromDb);
        return "addOrUpdate-user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){
        //here we call db/service to delete the object
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
