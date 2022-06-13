package jac.fsd02.foodorder.controller;

import jac.fsd02.foodorder.model.User;
import jac.fsd02.foodorder.repository.UserRepository;
import jac.fsd02.foodorder.service.AdminCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;
    final AdminCityService adminCityService;

    public AppController(AdminCityService adminCityService) {
        this.adminCityService = adminCityService;
    }


    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    @RequestMapping(value="/qufen")
    public String qufen(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().toString().equals("[USER]"))
            return "redirect:/userIndex";//如果是客户登录
        else
            return "redirect:/catogoryList";//如果是后台管理人员登录
    }

//    @GetMapping("/admin")
//    public String viewAdminHomePage() {
//        return "homeadmin";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userCity",adminCityService.getCityList());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
