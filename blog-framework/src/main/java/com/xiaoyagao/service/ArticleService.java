package com.xiaoyagao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyagao.domain.entity.Article;
import com.xiaoyagao.domain.entity.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult listhotarticle();
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
