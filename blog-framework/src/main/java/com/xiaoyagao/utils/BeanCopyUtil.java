package com.xiaoyagao.utils;

import org.mapstruct.TargetType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class BeanCopyUtil {
    //一对一拷贝
    public static <S,T> T copy(S source,Class<T> target){
        T t =null;
        try {
             t = target.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source,t);
        return t;
    }
    //集合拷贝

    public static <S,T> List<T> copyList(List<S> source,Class<T> clazz)  {
        return source.stream().map(o -> copy(o, clazz)).collect(Collectors.toList());
    }

}
