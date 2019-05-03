package com.gohelpfund.api.v1.wallets.services;

import com.gohelpfund.api.v1.wallets.model.Category;
import com.gohelpfund.api.v1.wallets.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(String categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category saveCategory(Category category){
        category.withCategoryId(UUID.randomUUID().toString());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category.getCategoryId());
    }

}
