package com.xiaoyagao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyagao.domain.Article;
import com.xiaoyagao.mapper.ArticleMapper;
import com.xiaoyagao.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class AriticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
}
