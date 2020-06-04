package com.wyf.myblog.service;

import com.wyf.myblog.dao.UserRepository;
import com.wyf.myblog.domain.User;
import com.wyf.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyf
 * @create 2020/5/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /*处理登录，服务端采用MD5加密存储密码*/
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Transactional
    @Override
    public boolean saveUser(String username, String nikename,String password, String avatar) {
        Integer type = 2;
        User user = findByUsername(username);
        if (user != null){
            return false;
        }
        User u = new User(username,nikename,password,avatar,type);
        userRepository.save(u);
        return true;
    }
}
