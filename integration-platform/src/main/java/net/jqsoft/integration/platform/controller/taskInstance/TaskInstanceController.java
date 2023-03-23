package net.jqsoft.integration.platform.controller.taskInstance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.TaskInstanceQueryBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import net.jqsoft.integration.platform.model.vo.DsDriverInfoVO;
import net.jqsoft.integration.platform.service.TaskInstanceService;
import net.jqsoft.integration.platform.util.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;


@RequestMapping("/taskInstance")
@RestController
@Api("任务实例管理")
public class TaskInstanceController extends BaseController {

    @Resource
    private TaskInstanceService taskInstanceService;

    @Resource
    private RestTemplate dsClient;

    /**
     * 查询全量的 任务实例日志
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public CommonResult<PageInfo<Map<String, Object>>> getConfigPage(TaskInstanceQueryBO req) {
        return CommonResult.success(taskInstanceService.queryListByPage(req));
    }
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/getDetails/{id}")
    public CommonResult<TaskInstance> getDetails(@PathVariable("id") String id) {
        return CommonResult.success(taskInstanceService.queryById(id));
    }
}
