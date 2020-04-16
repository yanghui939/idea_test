package com.yh.service;

import com.yh.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAllUser();

    /**
     * 模糊查询名字
     *
     * @param name
     * @return
     */
    public List<User> findByLike(String name);

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    public List<User> findByPag(Map<String, Object> map);

    /**
     * 查询总条数
     *
     * @return
     */
    public int count();

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public User findById(String id);

    /**
     * 插入数据
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user);

    /**
     * 修改数据
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteUser(String id);

    /**
     * 登录校验
     *
     * @param user
     * @return
     */
    public User login(User user);
}
