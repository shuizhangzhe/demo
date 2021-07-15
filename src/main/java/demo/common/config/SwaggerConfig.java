package demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Swagger 配置类
 * @author 水张哲
 * @date 2021年5月7日
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    /**
     * 配置了 Swagger 的 Docket 的 Bean 实例
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo());
    }

    /**
     * 配置 Swagger 信息
     */
    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("XXX", "", "XXX@qq.com");
        return new ApiInfo(
                "XXX系统API文档",
                "这里是文档描述",
                "v1.0.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
