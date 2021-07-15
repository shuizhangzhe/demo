package demo.common.except;

import com.alibaba.fastjson.JSONObject;
import demo.common.enumeration.StatusCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * 自定义业务逻辑错误抛出
 *
 * @author 水张哲
 * @date 2021年3月22日
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class CsscException extends RuntimeException {

    private int code;
    private String msg;
    private JSONObject response;

    public CsscException(StatusCodeEnum sce) {
        code = sce.getCode();
        msg = sce.getMsg();
    }

    public CsscException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CsscException(int code, String msg, JSONObject response) {
        this.code = code;
        this.msg = msg;
        this.response = response;
    }

    public CsscException(StatusCodeEnum sce, JSONObject response) {
        code = sce.getCode();
        msg = sce.getMsg();
        this.response = response;
    }

    public CsscException(StatusCodeEnum sce, String supplement) {
        code = sce.getCode();
        msg = sce.getMsg() + "(" + supplement + ")";
    }

    public CsscException(StatusCodeEnum sce, Throwable throwable) {
        code = sce.getCode();
        msg = sce.getMsg();
        log.error(throwable.getMessage(), throwable);
    }

    public CsscException(StatusCodeEnum sce, Throwable throwable, String supplement) {
        code = sce.getCode();
        msg = sce.getMsg() + "(" + supplement + ")";
        log.error(throwable.getMessage(), throwable);
    }
}
