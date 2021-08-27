package demo.service;

import demo.repository.vo.RoleAddRequestVO;

public interface RoleService {

    Long save(RoleAddRequestVO roleAddRequestVO);

    void ensureRoleTagNotExist(String tag);
}
