package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
@ApiModel("任务实例分页查询入参")
public class TaskInstanceQueryBO extends BaseQueryParams {

    @ApiModelProperty("任务名称")
    private String taskInstanceName;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("作业实例")
    private String processInstanceName;


    @ApiModelProperty("执行用户")
    private String executorName;
    @ApiModelProperty("主机")
    private String host;
    @ApiModelProperty("状态")
    private String stateType;
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;


}
