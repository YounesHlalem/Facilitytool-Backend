package edu.ap.facilitytoolspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.facilitytoolspringboot.models.Category;
import edu.ap.facilitytoolspringboot.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /***
     * Categorie creëren.
     * @param category
     * @return
     */
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    /***
     * categorieën ophalen vanuit MongoDB via mongoDb repository
     * @return
     */
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void deleteByName(String name) {
        Category c = categoryRepository.findByName(name);
        categoryRepository.delete(c);
    }
}
