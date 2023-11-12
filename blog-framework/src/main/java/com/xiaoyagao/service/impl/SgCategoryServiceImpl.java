package com.xiaoyagao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyagao.constants.SystemConstants;
import com.xiaoyagao.domain.entity.Article;
import com.xiaoyagao.domain.entity.ResponseResult;
import com.xiaoyagao.domain.entity.SgCategory;
import com.xiaoyagao.domain.vo.CategoryVo;
import com.xiaoyagao.mapper.SgCategoryMapper;
import com.xiaoyagao.service.ArticleService;
import com.xiaoyagao.service.SgCategoryService;
import com.xiaoyagao.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(SgCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-11-05 15:26:48
 */
@Service("sgCategoryService")
public class SgCategoryServiceImpl extends ServiceImpl<SgCategoryMapper, SgCategory> implements SgCategoryService {
    @Autowired
    ArticleService articleService;

    public ResponseResult getCategoryList(){
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleLambdaQueryWrapper);
        Set<Long> collect = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        List<SgCategory> sgCategories = listByIds(collect);
        List<SgCategory> result = sgCategories.stream()
                .filter(sgCategory -> SystemConstants.STATUS_NORMAL.equals(sgCategory.getStatus())).collect(Collectors.toList());
        List<CategoryVo> categoryVos = BeanCopyUtil.copyList(result, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
