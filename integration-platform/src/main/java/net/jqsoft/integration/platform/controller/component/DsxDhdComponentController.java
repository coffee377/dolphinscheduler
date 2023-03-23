package net.jqsoft.integration.platform.controller.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.common.JsonPage;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;
import net.jqsoft.integration.platform.mapstruct.DsxDhDCategoryMapStruct;
import net.jqsoft.integration.platform.mapstruct.DsxDhDComponentMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoQueryBO;
import net.jqsoft.integration.platform.model.bo.DsxDhdCategoryBO;
import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsDhdCategory;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
import net.jqsoft.integration.platform.model.entity.SysConfig;
import net.jqsoft.integration.platform.service.DsxDhdCategoryService;
import net.jqsoft.integration.platform.service.DsxDhdComponentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RequestMapping("/dsx_dnd_component")
@RestController
@Api("DHD组件信息")
public class DsxDhdComponentController extends BaseController {
    
    @Resource
    private DsxDhdComponentService dsxDhdComponentService;
    @Resource
    private DsxDhDComponentMapStruct dsxDhDComponentMapStruct;
    
    /**
     * 添加组件信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @PostMapping
    public CommonResult insert(@Validated({ValidationGroups.Insert.class}) @RequestBody DsxDhdComponentBO req) {
        return dsxDhdComponentService.insert(req);
    }
    
    /**
     * 修改组件信息
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    public CommonResult update(@Validated({ValidationGroups.Update.class}) @RequestBody DsxDhdComponentBO req) {

        return dsxDhdComponentService.update(req);
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<DxsDhdComponent> getDetails(@PathVariable("id") String id) {
        return CommonResult.success(dsxDhdComponentService.getById(id));
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
        dsxDhdComponentService.deleteConfigById(id);
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
        dsxDhdComponentService.deleteConfigBatch(ids);
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
    public CommonResult<JsonPage<DxsDhdComponent>> getConfigPage(DsxDhdComponentBO req) {
        QueryWrapper<DxsDhdComponent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(req.getName()), "name", req.getName());
        queryWrapper.eq(!ObjectUtils.isEmpty(req.getCid())  , "cid", req.getCid());
        queryWrapper.eq(!ObjectUtils.isEmpty(req.getRegistryId())  , "registry_id", req.getRegistryId());
        Page<DxsDhdComponent> pageList = dsxDhdComponentService.page(new Page<>(req.getPageNum(), req.getPageSize()));
        List<DxsDhdComponent> collect = pageList.getRecords().stream().collect(Collectors.toList());
        JsonPage<DxsDhdComponent> resultPage = new JsonPage<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal(), collect);
        return CommonResult.success(resultPage);
    }

    /**
     * 全量查询
     *
     * @return
     */
    @ApiOperation(value = "全量列表查询", notes = "全量列表查询")
    @GetMapping("/list")
    public CommonResult<List<DxsDhdComponent>> list(DsxDhdComponentBO req) {

        QueryWrapper<DxsDhdComponent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(req.getName()), "name", req.getName());
        queryWrapper.eq(!ObjectUtils.isEmpty(req.getCid())  , "cid", req.getCid());
        queryWrapper.eq(!ObjectUtils.isEmpty(req.getRegistryId())  , "registry_id", req.getRegistryId());
        return CommonResult.success(dsxDhdComponentService.list(queryWrapper));
    }
}
