package com.wyf.myblog.service;

import com.wyf.myblog.domain.User;

/**
 * @author wyf
 * @create 2020/5/27
 */
public interface UserService {
    User checkUser(String username, String password);
    User findByUsername(String username);
    boolean saveUser(String username,String nikename,String password,String avatar);
}
