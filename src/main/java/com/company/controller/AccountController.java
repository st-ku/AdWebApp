package com.company.controller;

import com.company.repository.FileUploadDAO;
import com.company.entity.Ad;
import com.company.entity.User;
import com.company.service.AdCategoryService;
import com.company.service.AdService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    @Autowired
    private AdService adService;
    @Autowired
    private UserService userService;
    @Autowired
    Ad ad;
    @Autowired
    AdCategoryService adCategoryService;
    @Autowired
    FileUploadDAO fileUploadDao;

    @GetMapping(value = "/myAccount")
    public ModelAndView listAdsWithCategory() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("myAccount");
        mav.addObject("listAds", adService.findAdsByUser_Id(user.getId()));
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:myAccount";
    }
}
