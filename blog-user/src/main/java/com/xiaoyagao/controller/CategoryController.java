package com.xiaoyagao.controller;


import com.xiaoyagao.domain.entity.ResponseResult;
import com.xiaoyagao.service.SgCategoryService;
import com.xiaoyagao.service.impl.SgCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private SgCategoryServiceImpl sgCategoryService;

    @RequestMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return sgCategoryService.getCategoryList();
    }

}
