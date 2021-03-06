package com.gohelpfund.api.v1.campaign_service.campaigns.model.category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.category.status.CategoryStatus;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@JsonPropertyOrder({"id", "name", "description", "image_url", "status"})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 4, max = 256)
    private String id;
    private String name;
    private String description;

    @JsonProperty("image_url")
    @URL
    @Size(min = 4, max = 256)
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

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
