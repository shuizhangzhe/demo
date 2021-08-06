package demo.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import demo.common.util.xss.XssJacksonDeserializer;
import demo.common.util.xss.XssJacksonSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

/**
 * Jackson 配置 Xss
 * @author 水张哲
 * @date 2021.07.30
 * */
@Configuration
public class JacksonConfig implements WebMvcConfigurer {

    /**
     * 别问我怎么写的,百度的
     * https://springboot.plus/guide/xss.html
     * 以上网址有残缺,相似案例补充
     * https://zhuanlan.zhihu.com/p/277834439
     * */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> optional = converters.stream()
                .filter(o -> o instanceof MappingJackson2HttpMessageConverter)
                .findFirst();
        if (optional.isPresent()) {
            MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) optional.get();
            ObjectMapper mapper = converter.getObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(String.class, new XssJacksonSerializer());
            simpleModule.addDeserializer(String.class, new XssJacksonDeserializer());
            mapper.registerModule(simpleModule);
        }
    }
}
