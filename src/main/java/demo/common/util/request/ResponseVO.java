package demo.common.util.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response 标准VO
 * @author 水张哲
 * @date 2021年1月4日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> {
    /** 返回代码 */
    private Integer code;
    /** 返回信息 */
    private String msg;
    /** 返回数据 */
    private T data;
}
