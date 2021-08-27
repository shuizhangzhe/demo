package demo.common.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标准状态代码枚举
 * @author 水张哲
 * @date 2021年5月7日
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusCodeEnum {
    // 操作失败
    Error(-1,"操作失败"),
    // 操作成功
    SUCCESS(0,"操作成功"),
    // 用户名或密码错误
    USERNAME_PASSWORD_ERROR(101,"用户名或密码错误"),
    // 用户名已存在
    USERNAME_EXISTED_ERROR(102, "用户名已存在"),
    // 用户名不存在
    USERNAME_NOT_EXISTED_ERROR(103, "用户名不存在"),
    // 验证码错误
    VERIFY_CODE_ERROR(104, "验证码错误"),
    // 角色已存在
    ROLE_EXISTED_ERROR(105, "角色已存在"),
    // 角色不存在
    ROLE_NOT_EXISTED_ERROR(106, "角色不存在"),
    // 参数异常
    PARAM_EXCEPTION(401,"参数异常"),
    // 数字格式转换错误
    NUMBER_FORMAT_ERROR(402,"数字格式转换错误"),
    // 404 NOT FOUND
    NOT_FOUND(404,"资源不存在"),
    // 获取TOKEN失败
    GET_TOKEN_FAIL(405,"获取TOKEN失败"),
    // 服务器异常
    SERVER_ERROR(500,"服务器异常"),
    // 数据库异常
    DATABASE_EXCEPTION(501,"数据库异常"),
    // 数据保存失败
    DATABASE_SAVE_FAIL(502,"数据保存失败"),
    // 数据库更新失败
    DATABASE_UPDATE_FAIL(503,"数据库更新失败"),
    // 数据库删除失败
    DATABASE_DELETE_FAIL(504,"数据库删除失败"),
    // 数据已存在
    DATA_ALREADY_EXISTS(505,"数据已存在"),
    // 数据不存在
    DATA_NOT_EXISTS(506,"数据不存在"),
    // 文件夹创建失败
    FOLDER_CREATE_ERROR(520,"文件夹创建失败"),
    // 空文件
    FILE_EMPTY_ERROR(521,"空文件"),
    // 空文件名
    FILENAME_EMPTY_ERROR(522,"空文件名"),
    // 未知错误
    UNKNOWN_ERROR(999,"未知错误"),
    // 未登录
    LOGIN_INVALID(65535,"未登录"),
    // 权限不足
    PERMISSION_DENIED(65534, "权限不足");

    private final int code;
    private final String msg;
}