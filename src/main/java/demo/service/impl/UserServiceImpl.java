package demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;
import demo.repository.mapper.UserMapper;
import demo.repository.po.User;
import demo.repository.po.UserExample;
import demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = CsscException.class)
    @Override
    public void deleteAll(List<Integer> ids) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdIn(ids);
        try{
            userMapper.deleteByExample(userExample);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.DATABASE_DELETE_FAIL, e);
        }
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectOne(Integer id) {
        return  userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<User> list = userMapper.selectByExample(new UserExample());
        return new PageInfo<>(list);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectByExample(new UserExample());
    }
}
