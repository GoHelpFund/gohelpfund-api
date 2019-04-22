package com.gohelpfund.api.v1.categories.repository;

import com.gohelpfund.api.v1.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByCategoryId(String categoryId);
    Category findByName(String name);
}
