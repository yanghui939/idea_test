package com.yh.service.impl;

import com.yh.dao.UserDao;
import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.utils.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findByLike(String name) {
        return userDao.findByLike(name);
    }

    @Override
    public List<User> findByPag(Map<String, Object> map) {
        return userDao.findByPag(map);
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public User findById(String id) {
        User user = userDao.findById(id);
        try {
            String decoder = Base64.decoder(user.getPassword());
            user.setPassword(decoder);
            redisTemplate.opsForValue().set("user",user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        String password = user.getPassword();
        try {
            String encoder = Base64.encoder(password);
            user.setPassword(encoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int insert = userDao.insertUser(user);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        String password = user.getPassword();
        try {
            String encoder = Base64.encoder(password);
            user.setPassword(encoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int update = userDao.updateUser(user);
        if (update > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        int delete = userDao.deleteUser(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        String password = user.getPassword();
        try {
            String encoder = Base64.encoder(password);
            user.setPassword(encoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User use = userDao.login(user);
        return use;
    }
}
