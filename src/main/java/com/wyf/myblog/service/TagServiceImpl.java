package com.wyf.myblog.service;

import com.wyf.myblog.dao.TagRepository;
import com.wyf.myblog.domain.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyf
 * @create 2020/5/30
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagDao;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        //如果数据库已存在tag就返回null
        Tag name = tagDao.findByName(tag.getName());
        if (name != null){
            return null;
        }else {
            return tagDao.save(tag);
        }

    }

    @Transactional
    @Override
    public void delete(Tag tag) {
        tagDao.delete(tag);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public Tag findTag(Long id) {
        return tagDao.getOne(id);
    }

    @Override
    public Page<Tag> TagList(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public List<Tag> TagList() {
        return tagDao.findAll();
    }

    /*根据传过来的ids字符串查找对应的Tag的集合*/
    @Override
    public List<Tag> TagList(String ids) {
        if (ids != null && !"".equals(ids) ){
            String[] ids2 = ids.split(",");
            ArrayList<Long> idList = new ArrayList<>();
            for (String id:ids2) {
                idList.add(Long.parseLong(id));
            }
            return tagDao.findAllById(idList);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Tag> TagList(Integer num) {
        /*根据所属的博客数目从高到低列出num个tag*/
        /*参数：第一个page是展示出查询结果的第几页，第二个，每一页的tag条数，第三个排序的方式:倒序，根据blogs.size*/
        Pageable pageable = PageRequest.of(0,num, Sort.by(Sort.Direction.DESC,"blogs.size"));
        return tagDao.findAllByBlogSize(pageable);
    }

    @Transactional
    @Override
    public Tag update(Long id, Tag tag) {
        Tag byId = tagDao.getOne(id);
        if (tagDao.findByName(tag.getName()) != null){
            /*更新失败*/
            return null;
        }
        BeanUtils.copyProperties(tag,byId);
        /*把更新后的值存到数据库中*/
        return tagDao.save(byId);
    }
}
