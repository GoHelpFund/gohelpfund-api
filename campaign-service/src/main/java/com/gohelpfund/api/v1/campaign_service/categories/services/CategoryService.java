package com.gohelpfund.api.v1.campaign_service.categories.services;

import com.gohelpfund.api.v1.campaign_service.categories.model.Category;
import com.gohelpfund.api.v1.campaign_service.categories.model.CategoryStatus;
import com.gohelpfund.api.v1.campaign_service.categories.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories == null) {
            logger.debug("GET | PostgreSQL | empty | categories size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | categories size: {}", categories.size());
        }
        return categories;
    }

    public Category getCategoryById(String categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        if (category == null) {
            logger.debug("GET | PostgreSQL | not found | category id: {}", category);
        } else {
            logger.debug("GET | PostgreSQL | found | category id: {}", category);
        }
        return category;
    }

    public Category saveCategory(Category category) {
        category
                .withCategoryId(UUID.randomUUID().toString())
                .withStatus(CategoryStatus.PENDING);

        Category newCategory = categoryRepository.save(category);
        logger.debug("POST | PostgreSQL | created | category id: {} ", newCategory.getCategoryId());

        return newCategory;
    }

    public Category updateCategory(Category category) {
        Category newCategory = categoryRepository.save(category);
        logger.debug("PUT | PostgreSQL | updated | category id: {} ", newCategory.getCategoryId());

        return newCategory;
    }

    private void deleteCategory(Category category) {
        categoryRepository.delete(category.getCategoryId());
        logger.debug("DELETE | PostgreSQL | removed | category id: {} ", category.getCategoryId());
    }

}
