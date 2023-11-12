package com.xiaoyagao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyagao.constants.SystemConstants;
import com.xiaoyagao.domain.entity.Article;
import com.xiaoyagao.domain.entity.ResponseResult;
import com.xiaoyagao.domain.entity.SgCategory;
import com.xiaoyagao.domain.vo.ArticleListVo;
import com.xiaoyagao.domain.vo.HotArticleVo;
import com.xiaoyagao.domain.vo.PageVo;
import com.xiaoyagao.mapper.ArticleMapper;
import com.xiaoyagao.service.ArticleService;
import com.xiaoyagao.service.SgCategoryService;
import com.xiaoyagao.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AriticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
    @Autowired
    SgCategoryService sgCategoryService;
    @Override
    public ResponseResult listhotarticle() {
        LambdaQueryWrapper<Article> articleLambdaQueryChainWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryChainWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleLambdaQueryChainWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 10);
        page(page,articleLambdaQueryChainWrapper);
        List<Article> records = page.getRecords();
        List<HotArticleVo> results=new ArrayList<>();
        List<HotArticleVo> hotArticleVos = BeanCopyUtil.copyList(records, HotArticleVo.class);
        return  ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);


        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleLambdaQueryWrapper.orderByDesc(Article::getIsTop);
        Page<Article> articlePage = new Page<>(pageNum,pageSize);
        List<Article> records = page(articlePage, articleLambdaQueryWrapper).getRecords();
//        for (Article article : records) {
//            SgCategory byId = sgCategoryService.getById(article.getCategoryId());
//            article.setCategoryName(byId.getName());
//        }
        records.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        SgCategory byId = sgCategoryService.getById(article.getCategoryId());
                        article.setCategoryName(byId.getName());
                        return article;
                    }
                }).collect(Collectors.toList());

        List<ArticleListVo> articleListVos = BeanCopyUtil.copyList(articlePage.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, articlePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
