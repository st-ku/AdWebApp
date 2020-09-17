package com.company.service;

import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Service
public class UserListener {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @EventListener
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        User user = this.userService.findUserByName(userDetails.getUsername());
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        user.setLastLoginDate(currentDate);
        userService.userRepository.save(user);
    }
}