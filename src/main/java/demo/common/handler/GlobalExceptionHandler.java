package demo.common.handler;

import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;
import demo.common.util.request.ParameterWrapperUtils;
import demo.common.util.request.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Date;


/**
 * 全局异常处理器
 *
 * @author hys
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return ResponseEntity
     */
    @ExceptionHandler(value = CsscException.class)
    public ResponseEntity<ResponseVO<?>> businessExceptionHandler(CsscException e) {
        log.error(new Date().toString());
        e.printStackTrace();
        // 输出异常消息
        return ResponseEntity.status(HttpStatus.OK).body(ParameterWrapperUtils.putCode2ResponseVO(e));

    }

    /**
     * 权限异常处理
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ResponseVO<?>> accessDeniedExceptionHandler(AccessDeniedException e){
        log.error(new Date().toString());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).
                body(ParameterWrapperUtils.putCode2ResponseVO(StatusCodeEnum.PERMISSION_DENIED));
    }

    /**
     * 其他异常处理
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseVO<?>> otherExceptionHandler(Exception e) {
        log.error(new Date().toString());
        e.printStackTrace();
        // 输出异常消息
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ParameterWrapperUtils.putCode2ResponseVO(StatusCodeEnum.UNKNOWN_ERROR));
    }
}
