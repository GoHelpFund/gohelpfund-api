package com.gohelpfund.api.v1.wallets.repository;

import com.gohelpfund.api.v1.wallets.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCategoryId(String categoryId);
    Optional<Category> findByName(String name);
}
