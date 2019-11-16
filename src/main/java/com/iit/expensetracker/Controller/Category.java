package com.iit.expensetracker.Controller;


import com.iit.expensetracker.Dto.CategoryDTObject;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController
public class Category extends ResponseController {
    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    public Category(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody CategoryDTObject categoryDTObject){
        return sendResponse(categoryService.saveCategory(categoryDTObject));
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getAllCategoriesByUserId(@RequestHeader("user") String user){
        return sendResponse(categoryService.getAllCategoriesByUserId(user));
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> editCategoryById(@RequestBody CategoryDTObject categoryDTObject, @PathVariable("id") String categoryId){
        logger.info("HIT-->Category controller edit category {} {}" , categoryId, categoryDTObject);
        return sendResponse(categoryService.editCategoryById(categoryDTObject,categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable("id")String categoryId){
        logger.info("HIT---> Category controller delete category {}",categoryId);
        return sendResponse(categoryService.deleteCategoryById(categoryId));
    }


}
