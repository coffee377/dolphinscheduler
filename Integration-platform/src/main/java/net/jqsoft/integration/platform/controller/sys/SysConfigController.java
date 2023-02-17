package net.jqsoft.integration.platform.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.common.JsonPage;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;
import net.jqsoft.integration.platform.mapstruct.SysConfigMapStruct;
import net.jqsoft.integration.platform.model.bo.SysConfigBO;
import net.jqsoft.integration.platform.model.bo.SysConfigQueryBO;
import net.jqsoft.integration.platform.model.entity.SysConfig;
import net.jqsoft.integration.platform.model.vo.SysConfigVO;
import net.jqsoft.integration.platform.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/sysConfig")
@RestController
@Api("系统参数管理")
public class SysConfigController extends BaseController {
    
    @Resource
    private SysConfigService sysConfigService;
    @Resource
    private SysConfigMapStruct sysConfigMapStruct;
    
    /**
     * 添加系统参数
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @PostMapping
    public CommonResult insert(@Validated({ValidationGroups.Insert.class}) @RequestBody SysConfigBO req) {
        sysConfigService.insert(req);
        return CommonResult.success();
    }
    
    /**
     * 修改系统参数
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    public CommonResult updateSysConfig(@Validated({ValidationGroups.Update.class}) @RequestBody SysConfigBO req) {
        sysConfigService.updateSysConfig(req);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<SysConfigVO> getDetails(@PathVariable("id") String id) {
        SysConfig sysConfig = sysConfigService.getById(id);
        return CommonResult.success(sysConfigMapStruct.toVO(sysConfig));
    }
    
    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/{id}")
    public CommonResult deleteConfigById(@PathVariable String id) {
        sysConfigService.deleteConfigById(id);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "批量删除", notes = "根据url的ids来批量删除对象")
    @ApiImplicitParam(name = "ids", value = "ID集合", required = true, dataType = "List", paramType = "path")
    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteConfigBatch(@PathVariable List<String> ids) {
        sysConfigService.deleteConfigBatch(ids);
        return CommonResult.success();
    }
    
    /**
     * 分页查询信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public CommonResult<JsonPage<SysConfigVO>> getConfigPage(SysConfigQueryBO req) {
        QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(req.getName()), "name", req.getName());
        Page<SysConfig> pageList = sysConfigService.page(new Page<>(req.getPageNum(), req.getPageSize()), queryWrapper);
        List<SysConfigVO> collect = pageList.getRecords().stream().map(sysConfigMapStruct::toVO).collect(Collectors.toList());
        JsonPage<SysConfigVO> resultPage = new JsonPage<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal(), collect);
        return CommonResult.success(resultPage);
    }
    /**
     * 刷新参数缓存
     *
     * @return
     */
//    @GetMapping("/refresh")
//    public CommonResult refreshConfig() {
//        sysConfigService.refreshConfig();
//        return CommonResult.success();
//    }
}
