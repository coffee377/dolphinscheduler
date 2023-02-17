package net.jqsoft.integration.platform.controller.logger;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapstruct.IpSegmentMapStruct;
import net.jqsoft.integration.platform.model.bo.IpSegmentBO;
import net.jqsoft.integration.platform.model.bo.IpSegmentQueryBO;
import net.jqsoft.integration.platform.model.bo.SysConfigQueryBO;
import net.jqsoft.integration.platform.model.entity.IpSegment;
import net.jqsoft.integration.platform.model.entity.IpSegmentVO;
import net.jqsoft.integration.platform.service.IpSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/logger")
@RestController
@Api("日志管理管理")
public class LoggerController extends BaseController {

    @Resource
    private IpSegmentService ipSegmentService;
    @Resource
    private IpSegmentMapStruct ipSegmentMapStruct;

    @Autowired
    @Qualifier(value = "registerTemplate")
    private RestTemplate restTemplate;

    @Value("${dataReporting.token}")
    private String token;

    @Value("${dataReporting.reportUrl}")
    private String reportUrl;
    


    /**
     * 查询全量的 任务实例日志
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
