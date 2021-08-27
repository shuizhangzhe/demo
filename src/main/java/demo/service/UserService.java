package demo.service;

import demo.repository.po.User;
import demo.repository.vo.UserRegisterRequestVO;
import demo.repository.vo.UserUpdateRequestVO;

public interface UserService {

    void save(UserRegisterRequestVO userRegisterRequestVO);

    User find(String userName);

    void update(UserUpdateRequestVO userUpdateRequestVO);

    void delete(String userName);

    boolean check(String currentPassword, String password);

    void ensureUserNameNotExist(String userName);
}
