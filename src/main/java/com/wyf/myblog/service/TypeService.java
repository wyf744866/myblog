package com.wyf.myblog.service;

import com.wyf.myblog.domain.Type;
import com.wyf.myblog.exceptionsolve.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wyf
 * @create 2020/5/30
 */
public interface TypeService {
    /*crud*/
    /*增*/
    Type saveType(Type type);

    /*删除*/
    void delete(Type type);

    void deleteById(Long id);

    /*查询*/
    Type getType(Long id);
    Page<Type> TypeList(Pageable pageable);
    List<Type> TypeList();
    /*查询几个tag*/
    List<Type> TypeList(Integer num);

    /*修改*/
    Type update(Long id,Type type) throws NotFoundException;
}
