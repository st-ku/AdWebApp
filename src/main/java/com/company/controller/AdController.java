package com.company.controller;

import com.company.repository.FileUploadDAO;
import com.company.entity.Ad;
import com.company.entity.AdCategory;
import com.company.entity.User;
import com.company.service.AdCategoryService;
import com.company.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Map;

@Controller

public class AdController {
    @Autowired
    private AdService adService;
    @Autowired
    private AdCategoryService adCategoryService;
    @Autowired
    Ad ad;
    @Autowired
    AdCategory adCategory;
    @Autowired
    FileUploadDAO fileUploadDao;

    @Autowired
    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    @Autowired
    public void setAdCategoryService(AdCategoryService adCategoryService) {
        this.adCategoryService = adCategoryService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/ads")
    public ModelAndView listAdsWithCategory(@RequestParam("category") String category, @RequestParam("id") long id, @RequestParam("id2") long id2) {
        ModelAndView mav = new ModelAndView("ads");
        String s = "";
        mav.addObject("searchString", s);
        mav.addObject("ads", ad);
        mav.addObject("id", id);
        mav.addObject("id2", id2);
        mav.addObject("category", category);
        mav.addObject("adCategory", adCategory);
        if (category.equals("All")) {
            mav.addObject("listAds", adService.listAds());
        } else mav.addObject("listAds", adService.findByAdCategoryCategoryName(category));
        return mav;
    }

    @GetMapping("/user_ads")
    public ModelAndView adsByUser(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("user_ads");
        mav.addObject("listAds", this.adService.findAdsByUser_Id(id));
        return mav;
    }
    @PostMapping("/search_ads")
    public ModelAndView adsByUser(@RequestParam(required = true, defaultValue = "") String action) {
       ModelAndView mav = new ModelAndView("user_ads");
        mav.addObject("listAds", this.adService.findByContentIgnoreCaseContaining(action));
        return mav;
    }

    @GetMapping("/nextAds")
    public ModelAndView nextAds(@RequestParam("category") String category, @RequestParam("id") long id, @RequestParam("id2") long id2) {
        id += 10;
        ModelAndView mav = new ModelAndView("ads");
        mav.addObject("ads", new Ad());
        mav.addObject("id", id);
        mav.addObject("id2", id2);
        mav.addObject("category", category);
        mav.addObject("listAds", adService.getTopAds(id, id2)); //
        return mav;
    }


    @RequestMapping("/remove/{id}")
    public ModelAndView removeAd(@PathVariable("id") Long id, ModelAndView mav) {
        this.adService.removeAd(id);
        return mav;
    }

    @RequestMapping("/ad_data")
    public ModelAndView adDataD(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("ad_data");
        mav.addObject("currentAd", this.adService.getAdById(id));
        return mav;
    }

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_MANAGER"})
    @RequestMapping("/new")
    public String newAdForm(Map<String, Object> model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.put("ad", ad);
        model.put("user", user);
        model.put("categoriesList", adCategoryService.listCategories());
        return "new_ad";
    }

    @PostMapping(value = "/save")
    public String saveAd(@ModelAttribute("ad") Ad adFromForm, @RequestParam CommonsMultipartFile[] fileUpload) throws SQLException {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        adFromForm.setUploadFile(adService.handleFileUpload(fileUpload));
        adService.saveAd(adFromForm, user);
        return "redirect:/";
    }





    }





