package com.company.service;

import com.company.entity.Ad;
import com.company.entity.AdCategory;
import com.company.entity.UploadFile;
import com.company.entity.User;
import com.company.repository.AdRepository;
import com.company.repository.AdRepositoryCustom;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdService {
    @Autowired
    AdRepository adRepository;
    @Autowired
    AdRepositoryCustom adRepositoryCustom;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdCategoryService adCategoryService;

    @Transactional
    public boolean saveAd(@Valid Ad ad, User user) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ad.setDate(date);
        ad.setUser(user);
        if (ad.getAdDaysAlive()==null) {
            ad.setAdDaysAlive(3);
        }
        AdCategory adCategory = adCategoryService.findAdCategoryByCategoryName(ad.getCategory());
        ad.setAdCategory(adCategory);
        adRepository.save(ad);
        return true;
    }

    @Transactional
    public boolean removeAd(Long id) {
        Ad adFromDB = adRepository.findAdsByAdId(id);
        if (adFromDB != null) {
            adRepository.delete(adFromDB);
            return true;
        } else return false;


    }

    public Ad getAdById(Long id) {
        return adRepository.findAdsByAdId(id);
    }

    @Transactional
    public List<Ad> listAds() {
        return adRepository.findAllByOrderByDateDesc();

    }

    public List getTopAds(Long currentId, Long targetId) {
        return this.adRepositoryCustom.getTopAds(currentId, targetId);
    }

    public List findByAdCategoryCategoryName(String adCategory) {
        return this.adRepository.findByAdCategoryCategoryNameOrderByDateDesc(adCategory);
    }

    public List findAdsByUser_Id(Long id) {
        return this.adRepository.findAdsByUser_IdOrderByDateDesc(id);
    }

    public List findAdsByAvailable(Boolean isAvailable) {
        return this.adRepository.findAdsByAvailableOrderByDateDesc(isAvailable);
    }

    public Ad createEditedAd(Ad adFromForm, Long id) {
        Ad adFromDB = getAdById(id);
        adFromDB.setContent(adFromForm.getContent());
        adFromDB.setCategory(adFromForm.getCategory());
        adFromDB.setAvailable(adFromForm.getAvailable());
        adFromDB.setPublicPhoneNumber(adFromForm.getPublicPhoneNumber());
        return adFromDB;
    }
    public List findByContentIgnoreCaseContaining(String word) {
       return this.adRepository.findByContentIgnoreCaseContaining(word);
    }
    public Set<UploadFile> handleFileUpload(CommonsMultipartFile[] fileUpload) throws SQLException {
        Set<UploadFile> uploadFileSet = new HashSet<UploadFile>();
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                uploadFileSet.add(uploadFile);
            }
        }
        return uploadFileSet;
    }
}
