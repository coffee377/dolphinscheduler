package net.jqsoft.integration.platform.controller.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.common.JsonPage;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;
import net.jqsoft.integration.platform.mapstruct.DsDriverInfoMapStruct;
import net.jqsoft.integration.platform.mapstruct.DsxJobMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoQueryBO;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.bo.DsxJobBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsJob;
import net.jqsoft.integration.platform.service.DsxComponentTypeService;
import net.jqsoft.integration.platform.service.DsxJobService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/dsx_job")
@RestController
@Api("作业信息")
public class DsxJobTypeController extends BaseController {
    
    @Resource
    private DsxJobService dsxJobService;
    @Resource
    private DsxJobMapStruct dsxJobMapStruct;
    
    /**
     * 添加作业信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @PostMapping
    public CommonResult insert(@Validated({ValidationGroups.Insert.class}) @RequestBody DsxJobBO req) {
        dsxJobService.insert(req);
        return CommonResult.success();
    }
    
    /**
     * 修改作业信息
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    public CommonResult update(@Validated({ValidationGroups.Update.class}) @RequestBody DsxJobBO req) {
        dsxJobService.update(req);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<DxsJob> getDetails(@PathVariable("id") Long id) {
        return CommonResult.success(dsxJobService.getById(id));
    }
    
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public CommonResult deleteConfigById(@PathVariable Long id) {
        dsxJobService.deleteConfigById(id);
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
    public CommonResult deleteConfigBatch(@PathVariable List<Long> ids) {
        dsxJobService.deleteConfigBatch(ids);
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
    public CommonResult<JsonPage<DxsJob>> getConfigPage(DsDriverInfoQueryBO req) {
        Page<DxsJob> pageList = dsxJobService.page(new Page<>(req.getPageNum(), req.getPageSize()));
        List<DxsJob> collect = pageList.getRecords().stream().collect(Collectors.toList());
        JsonPage<DxsJob> resultPage = new JsonPage<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal(), collect);
        return CommonResult.success(resultPage);
    }


}
