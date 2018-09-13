package com.gohelpfund.api.v1.campaigns.model.category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaigns.model.category.status.CategoryStatus;

import javax.persistence.*;


@JsonPropertyOrder({"name", "description", "image_url", "status"})
public class Category {

    @JsonIgnore
    private String id;
    private String name;
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Category withId(String categoryId) {
        this.setId(categoryId);
        return this;
    }

    public Category withName(String name) {
        this.setName(name);
        return this;
    }

    public Category withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public Category withImageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

}
