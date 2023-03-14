package net.jqsoft.integration.platform.controller.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.common.JsonPage;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;
import net.jqsoft.integration.platform.mapstruct.DsxDndRegisterMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoQueryBO;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.service.DsxComponentTypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/dsx_dnd_register")
@RestController
@Api("组件注册信息")
public class DsxDndRegistryController extends BaseController {
    
    @Resource
    private DsxComponentTypeService dsxComponentTypeService;
    @Resource
    private DsxDndRegisterMapStruct dndRegisterMapStruct;
    
    /**
     * 添加组件注册信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @PostMapping
    public CommonResult insert(@Validated({ValidationGroups.Insert.class}) @RequestBody DsxComponentTypeBO req) {
        dsxComponentTypeService.insert(req);
        return CommonResult.success();
    }
    
    /**
     * 修改组件注册信息
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    public CommonResult update(@Validated({ValidationGroups.Update.class}) @RequestBody DsxComponentTypeBO req) {
        dsxComponentTypeService.update(req);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<DxsComponentType> getDetails(@PathVariable("id") String id) {
        return CommonResult.success(dsxComponentTypeService.getById(id));
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
    public CommonResult deleteConfigById(@PathVariable Long id) {

        return dsxComponentTypeService.deleteConfigById(id);
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
        dsxComponentTypeService.deleteConfigBatch(ids);
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
    public CommonResult<JsonPage<DxsComponentType>> getConfigPage(DsDriverInfoQueryBO req) {
        Page<DxsComponentType> pageList = dsxComponentTypeService.page(new Page<>(req.getPageNum(), req.getPageSize()));
        List<DxsComponentType> collect = pageList.getRecords().stream().collect(Collectors.toList());
        JsonPage<DxsComponentType> resultPage = new JsonPage<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal(), collect);
        return CommonResult.success(resultPage);
    }

    /**
     * 全量查询
     *
     * @return
     */
    @ApiOperation(value = "全量列表查询", notes = "全量列表查询")
    @GetMapping("/list")
    public CommonResult<List<DxsComponentType>> list() {
        return CommonResult.success(dsxComponentTypeService.list());
    }
}
