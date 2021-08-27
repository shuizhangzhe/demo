package demo.common.util.request;

import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;

/**
 * 参数封装工具
 *
 * @see ResponseVO
 * @author 水张哲
 * @date 2021年1月4日
 */
public class ParameterWrapperUtils {

    public static <T> ResponseVO<T> successAndRenderData(T data) {
        ResponseVO<T> res = new ResponseVO<>();
        putCode2ResponseVO(res, StatusCodeEnum.SUCCESS);
        res.setData(data);
        return res;
    }

    public static ResponseVO<Void> successAndRender() {
        ResponseVO<Void> res = new ResponseVO<>();
        putCode2ResponseVO(res, StatusCodeEnum.SUCCESS);
        return res;
    }

    public static <T> ResponseVO<T> putCode2ResponseVO(ResponseVO<T> vo, StatusCodeEnum sc) {
        vo.setCode(sc.getCode());
        vo.setMsg(sc.getMsg());
        return vo;
    }

    public static <T> ResponseVO<T> putCode2ResponseVO(StatusCodeEnum sc) {
        ResponseVO<T> res = new ResponseVO<>();
        return putCode2ResponseVO(res, sc);
    }

    public static <T> ResponseVO<T> putCode2ResponseVO(int code, String msg) {
        ResponseVO<T> res = new ResponseVO<>();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    public static <T> ResponseVO<T> putCode2ResponseVO(int code, String msg, T response) {
        ResponseVO<T> res = new ResponseVO<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(response);
        return res;
    }

    public static <T> ResponseVO<T> putCode2ResponseVO(CsscException e) {
        ResponseVO<T> res = new ResponseVO<>();
        res.setCode(e.getCode());
        res.setMsg(e.getMsg());
        return res;
    }
}
