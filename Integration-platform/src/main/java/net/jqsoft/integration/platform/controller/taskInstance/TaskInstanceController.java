package net.jqsoft.integration.platform.controller.taskInstance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.TaskInstanceQueryBO;
import net.jqsoft.integration.platform.model.vo.TaskInstanceVO;
import net.jqsoft.integration.platform.service.TaskInstanceService;
import net.jqsoft.integration.platform.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping("/taskInstance")
@RestController
@Api("任务实例管理")
public class TaskInstanceController extends BaseController {

    @Resource
    private TaskInstanceService taskInstanceService;
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
            @ApiImplicitParam(name = "taskInstanceQueryBO", value = "查询实体taskInstanceQueryBO", required = true, dataTypeClass = TaskInstanceQueryBO.class)
    })
    @GetMapping("/page")
    public CommonResult<PageInfo<Map<String, Object>>> getConfigPage(TaskInstanceQueryBO req) {

        return CommonResult.success(taskInstanceService.queryListByPage(req));
    }
}
