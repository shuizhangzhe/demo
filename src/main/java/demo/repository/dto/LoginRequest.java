package demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户登录请求 DTO
 * @author 水张哲
 * @date 2021.07.26
 */
@Data
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private Boolean rememberMe;
}
