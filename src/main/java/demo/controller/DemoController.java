package demo.controller;

import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.Assert;
import demo.common.except.CsscException;
import demo.common.util.request.DistUtils;
import demo.common.util.request.ResponseVO;
import demo.repository.po.Demo;
import demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static demo.common.util.request.ParameterWrapperUtils.successAndRenderData;

/**
 * DemoController
 * @author 水张哲
 * @date 2021.07.30
 */
@Tag(name = "Demo")
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @Operation(summary = "Add", description = "Add", tags = {"Demo"})
    @PostMapping(value = "/add")
    public ResponseVO<Long> add(
            @Parameter(name = "demo", description = "demo") @RequestBody Demo demo
    ){
        return successAndRenderData(demoService.add(demo));
    }

    @Operation(summary = "Find", description = "Find", tags = {"Demo"})
    @GetMapping(value = "/find")
    public ResponseVO<Demo> find(
            @Parameter(name = "id", description = "id") @RequestParam Long id
    ){
        try{
            Assert.notNull(id);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION, e);
        }
        return successAndRenderData(demoService.find(id));
    }

    @Operation(summary = "Find2", description = "Find2", tags = {"Demo"})
    @GetMapping(value = "/find2")
    public String find2(
            @Parameter(name = "id", description = "id") @RequestParam Long id
    ){
        try{
            Assert.notNull(id);
        }catch (Exception e){
            throw new CsscException(StatusCodeEnum.PARAM_EXCEPTION, e);
        }
        return DistUtils.successAndRenderData(demoService.find(id).getCol1());
    }
}
