package com.company.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepositoryCustom {
    List getTopAds(Long currentId, Long targetId);
    // List getTopAdsWithCategory(Long currentId, Long targetId, String category); //bugged

}
