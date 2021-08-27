package demo.common.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * Security 配置类
 * @author 水张哲
 * @date 2021年7月26日
 */
public final class SecurityConstants {
    /** JWT签名密钥 */
    @Value("jwt.config.secret_key")
    public static String JWT_SECRET;

    public static final String ROLE_CLAIMS = "rol";

    /** rememberMe 为 false 的时候过期时间是1个小时 */
    public static final long EXPIRATION = 60 * 60L;

    /** rememberMe 为 true 的时候过期时间是7天 */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /** JWT token defaults */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    /** Swagger Whitelist  */
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    /** Druid Whitelist  */
    public static final String[] DRUID_WHITELIST = {
            "/druid/**"
    };

    /** System Whitelist */
    public static final String[] SYSTEM_WHITELIST = {

    };

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
