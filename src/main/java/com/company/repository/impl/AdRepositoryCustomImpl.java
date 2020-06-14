package com.company.repository.impl;

import com.company.repository.AdRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AdRepositoryCustomImpl implements AdRepositoryCustom {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List getTopAds(Long currentId, Long targetId) {
        return entityManager.createQuery("select a from Ad a where a.adId >:parameter order by a.adId").setParameter("parameter", currentId).setMaxResults(targetId.intValue()).getResultList();
    }
   /* public List getTopAdsWithCategory(Long currentId, Long targetId, String category) {       //bugged
       return entityManager.createQuery("select a from Ad a where a.adId >:parameter and a.adCategory=:parameter2 order by a.adId").setParameter("parameter",currentId).setParameter("parameter2", category).setMaxResults(targetId.intValue()).getResultList();
    }*/

}
