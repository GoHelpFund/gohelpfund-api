package com.gohelpfund.api.v1.campaign_service.categories.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Objects;

@JsonPropertyOrder({"categoryId", "name", "description", "imageUrl", "status"})
public class CategoryResource extends ResourceSupport {
    @Id
    @JsonProperty("id")
    private String categoryId;

    private String name;

    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CategoryResource that = (CategoryResource) o;
        return categoryId.equals(that.categoryId) &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                imageUrl.equals(that.imageUrl) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, name, description, imageUrl, status);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
