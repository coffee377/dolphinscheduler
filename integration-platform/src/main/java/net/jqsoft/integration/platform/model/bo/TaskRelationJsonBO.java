package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("数据源信息入参")
public class TaskRelationJsonBO implements Serializable {

    @ApiModelProperty("主键id")
    private String name;

    @ApiModelProperty("前置任务CODE")
    private Long preTaskCode;

    @ApiModelProperty("任务版本")
    private Integer preTaskVersion;

    @ApiModelProperty("当前任务CODE")
    private Long postTaskCode;

    @ApiModelProperty("任务版本")
    private Integer postTaskVersion;

    @ApiModelProperty("类型")
    private String conditionType;

}
