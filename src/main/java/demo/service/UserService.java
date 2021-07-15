package demo.service;

import com.github.pagehelper.PageInfo;
import demo.repository.po.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {

    /**
     * 添加用户信息
     *
     * @param user 财务信息
     */
    void insert(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void delete(Integer id);

    /**
     * 删除用户信息
     *
     * @param ids 用户ids
     */
    void deleteAll(List<Integer> ids);

    /**
     * 修改用户信息
     *
     * @param user 财务信息
     */
    void update(User user);

    /**
     * 查询某个用户信息
     *
     * @param id 用户id
     * @return 某个用户信息
     */
    User selectOne(Integer id);

    /**
     * 分页查询用户信息
     *
     * @param pageNum 当前页数
     * @param pageSize 记录最大条数
     * @param orderBy 排序条件 如："id desc, name asc"
     * @return 用户信息列表分页
     */
    PageInfo<User> selectPage(Integer pageNum, Integer pageSize, String orderBy);

    /**
     * 查询所有列表
     *
     * @return 用户信息列表
     */
    List<User> selectAll();
}
