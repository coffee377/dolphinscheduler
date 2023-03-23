package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
@ApiModel("任务实例分页查询入参")
public class TaskInstanceQueryBO extends BaseQueryParams {
    
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("任务实例名称")
    private String taskInstanceName;

    @ApiModelProperty("工作流实例名称")
    private String processInstanceName;

}
