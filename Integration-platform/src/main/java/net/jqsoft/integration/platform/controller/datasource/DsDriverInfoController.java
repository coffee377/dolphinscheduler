package net.jqsoft.integration.platform.controller.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.common.JsonPage;
import net.jqsoft.integration.platform.mapstruct.DsDriverInfoMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoQueryBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.vo.DsDriverInfoVO;
import net.jqsoft.integration.platform.service.DsDriverInfoService;
import net.jqsoft.integration.platform.validate.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/dsDriverInfo")
@RestController
@Api("数据源驱动信息")
public class DsDriverInfoController extends BaseController {
    
    @Resource
    private DsDriverInfoService dsDriverInfoService;
    @Resource
    private DsDriverInfoMapStruct dsDriverInfoMapStruct;
    
    /**
     * 添加数据源驱动信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @ApiImplicitParam(name = "req", value = "详细实体req", required = true, dataType = "dsDriverInfoBO")
    @PostMapping
    public CommonResult insert(@Validated({ValidationGroups.Insert.class}) @RequestBody DsDriverInfoBO req) {
        dsDriverInfoService.insert(req);
        return CommonResult.success();
    }
    
    /**
     * 修改数据源驱动信息
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "req", value = "详细实体req", required = true, dataType = "dsDriverInfoBO")
    })
    public CommonResult updatedsDriverInfo(@Validated({ValidationGroups.Update.class}) @RequestBody DsDriverInfoBO req) {
        
        dsDriverInfoService.updatedsDriverInfo(req);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<DsDriverInfoVO> getDetails(@PathVariable("id") String id) {
        DsDriverInfo dsDriverInfo = dsDriverInfoService.getById(id);
        return CommonResult.success(dsDriverInfoMapStruct.toVO(dsDriverInfo));
    }
    
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/{id}")
    public CommonResult deleteConfigById(@PathVariable String id) {
        dsDriverInfoService.deleteConfigById(id);
        return CommonResult.success();
    }
    
    /**
     * 根据批量id删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "根据url的ids来批量删除对象")
    @ApiImplicitParam(name = "ids", value = "ID集合", required = true, dataType = "List", paramType = "path")
    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteConfigBatch(@PathVariable List<String> ids) {
        dsDriverInfoService.deleteConfigBatch(ids);
        return CommonResult.success();
    }
    
    /**
     * 分页查询信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dsDriverInfoQueryBO", value = "查询实体dsDriverInfoQueryBO", required = true, dataTypeClass = DsDriverInfoQueryBO.class)
    })
    @GetMapping("/page")
    public CommonResult<JsonPage<DsDriverInfoVO>> getConfigPage(DsDriverInfoQueryBO req) {
        Page<DsDriverInfo> pageList = dsDriverInfoService.page(new Page<>(req.getPageNum(), req.getPageSize()));
        List<DsDriverInfoVO> collect = pageList.getRecords().stream().map(dsDriverInfoMapStruct::toVO).collect(Collectors.toList());
        JsonPage<DsDriverInfoVO> resultPage = new JsonPage<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal(), collect);
        return CommonResult.success(resultPage);
    }
}
