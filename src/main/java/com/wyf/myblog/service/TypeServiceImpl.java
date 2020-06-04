package com.wyf.myblog.service;

import com.wyf.myblog.dao.TypeRepository;
import com.wyf.myblog.domain.Type;
import com.wyf.myblog.exceptionsolve.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wyf
 * @create 2020/5/30
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        if (typeRepository.findByName(type.getName()) != null) {
            /*已经有数据了，不再保存重复的标签*/
            return null;
        }
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public void delete(Type type) {
        typeRepository.delete(type);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        if (typeRepository.findById(id).isPresent()){
            /*默认返回一个集合*/
            return typeRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    @Override
    public Page<Type> TypeList(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> TypeList() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public List<Type> TypeList(Integer num) {
        /*根据所属的博客数目从高到低列出num个tag*/
        /*参数：第一个page是展示出查询结果的第几页（从0开始），第二个，每一页的tag条数，第三个排序的方式:倒序，根据blogs.size*/
        Pageable pageable = PageRequest.of(0,num, Sort.by(Sort.Direction.DESC,"blogs.size"));
        return typeRepository.findAllByBlogSize(pageable);
    }

    @Transactional
    @Override
    public Type update(Long id, Type type) throws NotFoundException {
        if (typeRepository.findById(id).isPresent()){
            Type type1 = typeRepository.findById(id).get();
            /*type已经存在，有数据了，不再保存重复的标签*/
            if (typeRepository.findByName(type.getName()) != null){
                return null;
            }
            /*把type的值赋值给type1*/
            BeanUtils.copyProperties(type,type1);
            /*把更新后的值存到数据库中*/
            return typeRepository.save(type1);

        }
        throw new NotFoundException("找不到该类别~");
    }
}
