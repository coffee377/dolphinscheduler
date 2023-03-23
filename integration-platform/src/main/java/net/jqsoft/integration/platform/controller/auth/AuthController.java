package net.jqsoft.integration.platform.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.LoginBO;
import net.jqsoft.integration.platform.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/dsDriverInfo")
@RestController
@Api("登录认证接口")
public class AuthController extends BaseController {
    
    @Resource
    private AuthService authService;
    
    
    /**
     * 添加数据源驱动信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @PostMapping("/authLogin")
    public CommonResult<String> authLogin(@Validated  @RequestBody LoginBO req) {
        CommonResult<String> commonResult = authService.authLogin(req);
        return commonResult;
    }
}