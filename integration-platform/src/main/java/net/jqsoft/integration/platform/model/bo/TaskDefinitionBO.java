package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("作业创建入参")
public class TaskDefinitionBO implements Serializable {

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("作业编码")
    private Long code;

    @ApiModelProperty("作业名称")
    private String name;

    @ApiModelProperty("项目详情")
    private ProjectBO project;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("任务实例")
    private List<TaskBO> tasks;

}
