package demo.common.filter;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器将直接用于用户身份验证，
 * 它将检查 URL 中的用户名和密码参数，
 * 并调用 Spring 的身份验证管理器来验证它们。
 * 如果用户名和密码正确，则过滤器将创建一个 JWT 令牌并在 HTTP 授权标头中返回它。
 * @author 水张哲
 * @date 2021.07.26
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var userName = request.getParameter(USERNAME).trim();
        var password = request.getParameter(PASSWORD).trim();
        var authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        /// return super.attemptAuthentication(request, response);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // TODO 这里是用户成功登陆后存储用户信息（用户权限），封装JWT的地方
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
