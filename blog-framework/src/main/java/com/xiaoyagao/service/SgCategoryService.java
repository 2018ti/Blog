package com.xiaoyagao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyagao.domain.entity.ResponseResult;
import com.xiaoyagao.domain.entity.SgCategory;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2023-11-05 15:26:47
 */
public interface SgCategoryService extends IService<SgCategory> {
    public ResponseResult getCategoryList();
}
