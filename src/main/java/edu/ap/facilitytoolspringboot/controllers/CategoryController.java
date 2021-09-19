package edu.ap.facilitytoolspringboot.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ap.facilitytoolspringboot.models.Category;
import edu.ap.facilitytoolspringboot.services.CategoryService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAll() {
        try {
            List<Category> categories = categoryService.getAll();
            if (categories.isEmpty()) {
                LOG.info("There are no categories to return");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all categories");
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the categories", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> postCategory(@RequestBody Category category) {
        try {
            Category cat = categoryService.create(category);
            LOG.info("Created a new category");
            return new ResponseEntity<>(cat, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't create a new category", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/categories/by-name/{name}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("name") String name) {
        try {
            categoryService.deleteByName(name);
            LOG.info("Deleted the category with the name: {}", name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error("Couldn't delete the category with the name: {}", name);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
