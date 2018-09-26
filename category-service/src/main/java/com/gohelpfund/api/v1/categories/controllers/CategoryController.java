package com.gohelpfund.api.v1.categories.controllers;

import com.gohelpfund.api.v1.categories.controllers.assembler.CategoryResourceAssembler;
import com.gohelpfund.api.v1.categories.controllers.exceptions.CategoryNotFoundException;
import com.gohelpfund.api.v1.categories.model.Category;
import com.gohelpfund.api.v1.categories.model.CategoryStatus;
import com.gohelpfund.api.v1.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryResourceAssembler assembler;


    @GetMapping()
    public Resources<Resource<Category>> all() {

        List<Resource<Category>> categories = service.getCategories().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(categories,
                linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Category> one(@PathVariable("id") String categoryId) {

        return assembler.toResource(
                service.getCategoryById(categoryId)
                        .orElseThrow(() -> new CategoryNotFoundException(categoryId)));
    }

    @PostMapping()
    public ResponseEntity<Resource<Category>> newCategory(@RequestBody Category category) {
        category.setStatus(CategoryStatus.PENDING);
        Category newCategory = service.saveCategory(category);

        return ResponseEntity
                .created(linkTo(methodOn(CategoryController.class).one(newCategory.getCategoryId())).toUri())
                .body(assembler.toResource(newCategory));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable("id") String categoryId) {
        Category category = service.getCategoryById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (category.getStatus() == CategoryStatus.PENDING) {
            category.setStatus(CategoryStatus.CANCELLED);
            return ResponseEntity.ok(assembler.toResource(service.updateCategory(category)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel an category that is in the " + category.getStatus() + " status"));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ResourceSupport> complete(@PathVariable("id") String categoryId) {

        Category category = service.getCategoryById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (category.getStatus() == CategoryStatus.PENDING) {
            category.setStatus(CategoryStatus.CREATED);
            return ResponseEntity.ok(assembler.toResource(service.updateCategory(category)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete an category that is in the " + category.getStatus() + " status"));
    }

}
