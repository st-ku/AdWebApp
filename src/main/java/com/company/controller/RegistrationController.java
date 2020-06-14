package com.company.controller;

import com.company.captcha.CaptchaService;
import com.company.captcha.CaptchaSettings;
import com.company.entity.User;
import com.company.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    CaptchaSettings captchaSettings;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("siteV3", captchaSettings.getSiteV3());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("g-recaptcha-response") String gRecaptchaResponse, @ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!captchaService.verify(gRecaptchaResponse)) {
            model.addAttribute("passwordError", "Captcha Error, try again");
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Password isn't match");
            return "registration";
        }
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(userForm.getEmail())) {
            model.addAttribute("emailError", "Wrong Email");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "User with this Username Already Exists");
            return "registration";
        }


        return "redirect:/";
    }
}
