package net.jqsoft.integration.platform.controller.segment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapstruct.IpSegmentMapStruct;
import net.jqsoft.integration.platform.mapstruct.SysConfigMapStruct;
import net.jqsoft.integration.platform.model.bo.IpSegmentBO;
import net.jqsoft.integration.platform.model.bo.IpSegmentQueryBO;
import net.jqsoft.integration.platform.model.bo.SysConfigBO;
import net.jqsoft.integration.platform.model.bo.SysConfigQueryBO;
import net.jqsoft.integration.platform.model.entity.IpSegment;
import net.jqsoft.integration.platform.model.entity.IpSegmentVO;
import net.jqsoft.integration.platform.model.entity.SysConfig;
import net.jqsoft.integration.platform.model.vo.SysConfigVO;
import net.jqsoft.integration.platform.service.IpSegmentService;
import net.jqsoft.integration.platform.service.SysConfigService;
import net.jqsoft.integration.platform.validate.ValidationGroups;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/ipsegment")
@RestController
@Api("IP白名单管理")
public class IpSegmentController extends BaseController {
    
    @Resource
    private IpSegmentService ipSegmentService;
    @Resource
    private IpSegmentMapStruct ipSegmentMapStruct;
    
    /**
     * 添加IP白名单
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加信息", notes = "添加信息")
    @ApiImplicitParam(name = "req", value = "详细实体req", required = true, dataType = "IpSegmentBO")
    @PostMapping
    public CommonResult insert( @RequestBody IpSegmentBO req) {
        ipSegmentService.insert(req);
        return CommonResult.success();
    }
    
    /**
     * 修改IP白名单
     *
     * @param req
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改信息", notes = "根据url的id来指定修改对象，并根据传过来的信息来修改详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "req", value = "详细实体req", required = true, dataType = "IpSegmentBO")
    })
    public CommonResult updateIpSegment(@RequestBody IpSegmentBO req) {

        ipSegmentService.updateIpSegment(req);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<IpSegmentVO> getDetails(@PathVariable("id") String id) {
        IpSegment ipSegment = ipSegmentService.getById(id);
        return CommonResult.success(ipSegmentMapStruct.toVO(ipSegment));
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
        ipSegmentService.deleteIpSegmentById(id);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "批量删除", notes = "根据url的ids来批量删除对象")
    @ApiImplicitParam(name = "ids", value = "ID集合", required = true, dataType = "List", paramType = "path")
    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteConfigBatch(@PathVariable List<String> ids) {
        ipSegmentService.deleteIpSegmentBatch(ids);
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
            @ApiImplicitParam(name = "SysConfigQueryBO", value = "查询实体sysConfigQueryBO", required = true, dataTypeClass = SysConfigQueryBO.class)
    })
    @GetMapping("/page")
    public CommonResult<Page<IpSegmentVO>> getConfigPage(IpSegmentQueryBO req) {
        QueryWrapper<IpSegment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(req.getIpSegment()) ,"IP_SEGMENT", req.getIpSegment());
        queryWrapper.eq(!StringUtils.isEmpty(req.getUserId()) ,"USER_ID", req.getUserId());
        Page<IpSegment> pageList = ipSegmentService.page(new Page<>(req.getPageNum(), req.getPageSize()), queryWrapper);
        List<IpSegmentVO> collect = pageList.getRecords().stream().map(ipSegmentMapStruct::toVO).collect(Collectors.toList());
        Page<IpSegmentVO> voPage = new Page<>(req.getPageNum(), req.getPageSize());
        voPage.setRecords(collect);
        return CommonResult.success(voPage);
    }
}
