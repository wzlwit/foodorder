package jac.fsd02.foodorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    UserDetailsService customUserDetailsService;

    public Long getUserIdFromSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        CustomUserDetails userDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername(userName);
        return userDetails.getId();
    }
}
