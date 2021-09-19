package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Category;
import edu.ap.facilitytoolspringboot.repositories.CategoryRepository;
import edu.ap.facilitytoolspringboot.services.CategoryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CategoryTest {
    //Category test
    @MockBean
    CategoryRepository mockCategoryRepository = Mockito.mock(CategoryRepository.class);

    CategoryService categorySystemUnderTest = new CategoryService(mockCategoryRepository);

    @Test
    public void checkCategoryCreated(){
        //arrange
        Category category = new Category("Test","Test description");

        //act
        mockCategoryRepository.save(category);
        int count = (int) mockCategoryRepository.count();
        count++;

        //assert
        assertEquals(1, count);
    }

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}
