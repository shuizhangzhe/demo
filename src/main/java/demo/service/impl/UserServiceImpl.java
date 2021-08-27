package demo.service.impl;

import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;
import demo.repository.mapper.UserMapper;
import demo.repository.mapper.UserRoleMapper;
import demo.repository.po.User;
import demo.repository.po.UserExample;
import demo.repository.po.UserRole;
import demo.repository.vo.RoleAddRequestVO;
import demo.repository.vo.UserRegisterRequestVO;
import demo.repository.vo.UserUpdateRequestVO;
import demo.service.RoleService;
import demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private RoleService roleService;

    @Override
    @Transactional(rollbackFor = CsscException.class)
    public void save(UserRegisterRequestVO userRegisterRequestVO) {
        try{
            ensureUserNameNotExist(userRegisterRequestVO.getUserName());
            User user = userRegisterRequestVO.toUser();
            user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequestVO.getPassword()));
            user.setCreatedAt(new Date());
            userMapper.insertSelective(user);

            RoleAddRequestVO roleAddRequestVO = new RoleAddRequestVO();
            roleAddRequestVO.setTag("DEFAULT");
            roleAddRequestVO.setName("普通用户");
            roleAddRequestVO.setDescription("创建用户时产生的默认角色");
            Long roleId = roleService.save(roleAddRequestVO);

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRole.setCreatedAt(new Date());
            userRoleMapper.insertSelective(userRole);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.Error, "注册用户失败!");
        }
    }

    @Override
    public User find(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.isEmpty()){
            throw new CsscException(StatusCodeEnum.USERNAME_NOT_EXISTED_ERROR, "用户 [" + userName + "] 不存在!");
        }else {
            return userList.get(0);
        }
    }

    @Override
    public void update(UserUpdateRequestVO userUpdateRequestVO) {
        String userName = userUpdateRequestVO.getUserName();
        try{
            User user = find(userName);
            user.setFullName(userUpdateRequestVO.getFullName());
            user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequestVO.getPassword()));
            user.setEnabled(userUpdateRequestVO.getEnabled());

            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserNameEqualTo(userName);
            userMapper.updateByExampleSelective(user, userExample);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.DATABASE_UPDATE_FAIL, "用户 [" + userName + "] 更新失败!");
        }
    }

    @Override
    public void delete(String userName) {
        try{
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserNameEqualTo(userName);
            userMapper.deleteByExample(userExample);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.DATABASE_DELETE_FAIL, "用户 [" + userName + "] 删除失败!");
        }
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    @Override
    public void ensureUserNameNotExist(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        if(userMapper.selectByExample(userExample).isEmpty()){
            throw new CsscException(StatusCodeEnum.USERNAME_EXISTED_ERROR, "用户 [" + userName + "] 已存在!");
        }
    }
}
