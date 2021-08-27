package demo.service.impl;

import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;
import demo.repository.mapper.RoleMapper;
import demo.repository.po.Role;
import demo.repository.po.RoleExample;
import demo.repository.vo.RoleAddRequestVO;
import demo.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Long save(RoleAddRequestVO roleAddRequestVO) {
        ensureRoleTagNotExist(roleAddRequestVO.getTag());
        Role role = roleAddRequestVO.toRole();
        role.setCreatedAt(new Date());
        roleMapper.insertSelective(role);
        return role.getId();
    }

    @Override
    public void ensureRoleTagNotExist(String tag) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andTagEqualTo(tag);
        if(roleMapper.selectByExample(roleExample).isEmpty()){
            throw new CsscException(StatusCodeEnum.ROLE_EXISTED_ERROR);
        }
    }
}
