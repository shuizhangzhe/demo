package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.Assert;
import demo.common.except.CsscException;
import demo.common.util.request.ResponseVO;
import demo.repository.po.User;
import demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static demo.common.util.request.ParameterWrapperUtils.successAndRender;
import static demo.common.util.request.ParameterWrapperUtils.successAndRenderData;

/**
 * 用户管理
 *
 * @author Administrator
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Operation(summary = "新增用户信息", description = "新增用户信息", tags = {"用户管理"})
    @PostMapping("/save")
    public ResponseVO<?> save(
            @Parameter(name = "user", description = "用户信息") @RequestBody User user
    ){
        try {
            Assert.hasText(user.getUsername());
            Assert.hasText(user.getPassword());
            Assert.hasText(user.getName());
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION);
        }
        userService.insert(user);
        return successAndRender();
    }

    @Operation(summary = "删除用户信息", description = "删除用户信息", tags = {"用户管理"})
    @DeleteMapping("/del/{id}")
    public ResponseVO<?> del(
            @PathVariable Integer id
    ){
        try {
            Assert.notNull(id);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION);
        }
        userService.delete(id);
        return successAndRender();
    }

    @Operation(summary = "删除用户信息", description = "删除用户信息", tags = {"用户管理"})
    @DeleteMapping("/delAll/{ids}")
    public ResponseVO<?> delAll(
            @PathVariable Integer[] ids
    ){
        List<Integer> idList = Arrays.asList(ids.clone());
        try {
            Assert.notEmpty(idList);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION);
        }
        userService.deleteAll(idList);
        return successAndRender();
    }

    @Operation(summary = "编辑用户信息", description = "编辑用户信息", tags = {"用户管理"})
    @PutMapping("/modify")
    public ResponseVO<?> modify(
            @Parameter(name = "user", description = "用户信息") @RequestBody User user
    ){
        try {
            Assert.notNull(user.getId());
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION);
        }
        userService.update(user);
        return successAndRender();
    }

    @Operation(summary = "获取某个用户信息", description = "获取某个用户信息", tags = {"用户管理"})
    @GetMapping("/get/{id}")
    public ResponseVO<User> getOne(
            @PathVariable Integer id
    ){
        try {
            Assert.notNull(id);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION);
        }
        return successAndRenderData(userService.selectOne(id));
    }

    @Operation(summary = "分页获取用户信息", description = "分页获取用户信息", tags = {"用户管理"})
    @GetMapping("/getPage")
    public ResponseVO<PageInfo<User>> getPage(
            @Parameter(name = "pageNum", description = "当前页数") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(name = "pageSize", description = "记录最大条数") @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(name = "orderBy", description = "排序条件") @RequestParam(defaultValue = "id") String orderBy
    ){
        return successAndRenderData(userService.selectPage(pageNum, pageSize, orderBy));
    }

    @Operation(summary = "获取全部用户信息", description = "获取全部用户信息", tags = {"用户管理"})
    @GetMapping("/getAll")
    public ResponseVO<List<User>> getAll(){
        return successAndRenderData(userService.selectAll());
    }
}
