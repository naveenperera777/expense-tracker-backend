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

    @PostMapping("/save")
    public ResponseEntity<Object> saveCategory(@RequestBody CategoryDTObject categoryDTObject){
        return sendResponse(categoryService.saveNewCategory(categoryDTObject));
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Object> retrieveCategoryByCategoryId(@PathVariable("category_id") String categoryId){
        return sendResponse(categoryService.retrieveCategoryByCategoryId(categoryId));
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getAllCategoriesForAUserByUserId(@RequestHeader("user_id") String user_id){
        return sendResponse(categoryService.getAllCategoriesForAUserByUserId(user_id));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories(){
        return sendResponse(categoryService.getAllCategories());
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Object> categoryDeleteById(@PathVariable("category_id")String category_id){
        return sendResponse(categoryService.categoryDeleteById(category_id));
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<Object> categoryEditById(@RequestBody CategoryDTObject categoryDTObject, @PathVariable("category_id") String category_id){
        return sendResponse(categoryService.categoryEditById(categoryDTObject,category_id));
    }


}
