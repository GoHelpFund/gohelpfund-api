package com.gohelpfund.api.v1.campaign_service.categories.repository;

import com.gohelpfund.api.v1.campaign_service.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByCategoryId(String categoryId);
    Category findByName(String name);
}
