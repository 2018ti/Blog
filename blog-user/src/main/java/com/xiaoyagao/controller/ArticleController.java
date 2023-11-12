package com.xiaoyagao.controller;

import com.xiaoyagao.domain.entity.Article;
import com.xiaoyagao.domain.entity.ResponseResult;
import com.xiaoyagao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/getall")
    public List<Article> test(){
        List<Article> list = articleService.list();
        return list;
    }
    @GetMapping("/hotArticleList")
    public ResponseResult hotarticle(){
        return articleService.listhotarticle();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pagenum,Integer pageSize,Long categoryid){

        return  articleService.articleList(pagenum,pageSize,categoryid);
    }

}
