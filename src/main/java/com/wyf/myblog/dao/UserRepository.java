package com.wyf.myblog.dao;

import com.wyf.myblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wyf
 * @create 2020/5/27
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
}
