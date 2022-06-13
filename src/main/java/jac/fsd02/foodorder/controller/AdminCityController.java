package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.City;
import jac.fsd02.foodorder.service.AdminCityService;
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
public class AdminCityController {
    final AdminCityService adminCityService;

    public AdminCityController(AdminCityService adminCityService){
        this.adminCityService = adminCityService;
    }
    @GetMapping("/city")
    public String homePage(Model model){
        //it will call the db/service and fetches the data
        List<City> cityList = adminCityService.getCityList();
        model.addAttribute("allCity",cityList);
        return "cityList";
    }

    @GetMapping("/addNewCity")
    public String addNewCity(Model model){
        City city = new City();
        model.addAttribute("city", city);
        return "addOrUpdate-city";
    }

    @PostMapping("/saveCity")
    public String saveCity(@Valid @ModelAttribute("city") City addCity, BindingResult result){
        //if I have validation issues
        if (result.hasErrors()){
            //I need to stay in the current page !!!!
            return "addOrUpdate-city";
        }
        adminCityService.saveOrUpdateCity(addCity);

        return "redirect:/city";
    }
    @GetMapping("/showFormForUpdateCity/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model)throws RecordNotFoundException {
        //now model needs to be populated with the information that comes from db
        //you have your id-> so you can fetch information from database
        City cityFromDb = adminCityService.getCityById(id);
        model.addAttribute("city", cityFromDb);
        return "addOrUpdate-city";
    }

    @GetMapping("/deleteCity/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){
        //here we call db/service to delete the object
        adminCityService.deleteCity(id);
        return "redirect:/city";
    }
}
