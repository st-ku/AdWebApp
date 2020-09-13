package com.company.controller;

import com.company.entity.Ad;
import com.company.entity.User;
import com.company.service.AdCategoryService;
import com.company.service.AdService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_MANAGER"})
public class ManagerController {
    @Autowired
    private AdService adService;
    @Autowired
    Ad ad;
    @Autowired
    AdCategoryService adCategoryService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/manageAds")
    public ModelAndView listAdsWithCategory() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("manageAds");
        if (user.getRoles().iterator().next().getName().equals("ROLE_ADMIN")) {
            mav.addObject("listAds", adService.listAds());
            return mav;
        }
        if (user.getRoles().iterator().next().getName().equals("ROLE_MANAGER")) {
            mav.addObject("listAds", adService.findAdsByAvailable(false));
            return mav;
        }
        else mav.addObject("listAds", adService.findAdsByUser_Id(user.getId()));
        return mav;
    }

    @RequestMapping("/edit")
    public String editAd(@RequestParam Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("ad", this.adService.getAdById(id));
        model.addAttribute("categoriesList", adCategoryService.listCategories());
        if (user.getRoles().iterator().next().getName().equals("ROLE_ADMIN")) {
            model.addAttribute("listAds", adService.listAds());
        } else model.addAttribute("listAds", adService.findAdsByAvailable(false));
        return "manageAds";
    }

    @RequestMapping("/delete")
    public String removeAd(@ModelAttribute("id") Long id) throws Exception {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (user.getRoles().iterator().next().getName().equals("ROLE_USER")) {
           if (adService.getAdById(id).getUser().getId().equals(user.getId())) {
               this.adService.removeAd(id);
               return "redirect:manageAds";
           }
           else throw new Exception("Access error");
        }




        this.adService.removeAd(id);
        return "redirect:manageAds";
    }

    @PostMapping(value = "/saveEdited")
    public String saveAdEdited(@ModelAttribute("ad") Ad adFromForm) {
        User user = adService.getAdById(adFromForm.getAdId()).getUser();
        adService.saveAd(adService.createEditedAd(adFromForm, adFromForm.getAdId()), user);
        return "redirect:manageAds";
    }


}
