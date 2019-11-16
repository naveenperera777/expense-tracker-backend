package com.iit.expensetracker.Controller;


import com.iit.expensetracker.Dto.CategoryDto;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController
public class CategoryController extends ResponseController {
    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody CategoryDto categoryDto){
        return sendResponse(categoryService.saveCategory(categoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") String categoryId){
        logger.info("Retrieve category id {}", categoryId);
        return sendResponse(categoryService.getCategoryById(categoryId));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories(){
        logger.info("Retrieve all categories");
        return sendResponse(categoryService.getAllCategories());
    }


}
