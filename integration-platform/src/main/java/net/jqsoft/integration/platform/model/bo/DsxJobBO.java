package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("数据源信息入参")
public class DsxJobBO implements Serializable {
    /**
     * 主键id
     */
    @NotBlank(message = "主键id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 标题
     */
    @NotBlank(message = "配置内容不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "配置内容",required = true)
    private String content;
    /**
     * 驱动程序类名
     */
    @ApiModelProperty(value = "配置内容哈希值")
    private Integer contentHash;
    /**
     * 驱动地址
     */
    @ApiModelProperty(value = "项目ID")
    private Long projectId;
    /**
     * 作业ID
     */
    @ApiModelProperty(value = "作业ID")
    private String jobId;

    /**
     * 作业ID
     */
    @ApiModelProperty(value = "是否推送到调度")
    private Integer push;
    
}
