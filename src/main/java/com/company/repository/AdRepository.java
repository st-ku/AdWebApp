package com.company.repository;

import com.company.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findAdsByAdId(Long id);

    List<Ad> findAllByOrderByDateDesc();

    List<Ad> findByAdCategoryCategoryNameOrderByDateDesc(String adCategory);

    List<Ad> findAdsByUser_IdOrderByDateDesc(Long id);

    List<Ad> findAdsByAvailableOrderByDateDesc(Boolean isAvailable);
    List<Ad> findByContentIgnoreCaseContaining(String content);
}