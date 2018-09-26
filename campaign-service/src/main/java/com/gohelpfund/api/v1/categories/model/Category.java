package com.gohelpfund.api.v1.categories.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "categories")
@JsonPropertyOrder({"categoryId", "name", "description", "imageUrl", "status"})
public class Category {
    @Id
    @JsonProperty("id")
    private String categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @JsonProperty("image_url")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @JsonIgnore
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public Category withCategoryId(String categoryId){
        this.setCategoryId(categoryId);
        return this;
    }

    public Category withName(String name){
        this.setName(name);
        return this;
    }

    public Category withDescription(String description){
        this.setDescription(description);
        return this;
    }

    public Category withImageUrl(String imageUrl){
        this.setImageUrl(imageUrl);
        return this;
    }

}
