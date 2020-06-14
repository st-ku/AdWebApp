package com.company.repository;

import com.company.entity.AdCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdCategoryRepository extends JpaRepository<AdCategory, Integer> {

    AdCategory findAdCategoryByCategoryId(Long id);
    AdCategory findAdCategoryByCategoryName(String name);

}
