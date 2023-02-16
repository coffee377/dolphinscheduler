package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.model.enums.IsEnableEnum;
import net.jqsoft.integration.platform.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("添加系统参数配置入参")
public class SysConfigBO implements Serializable {
    
    @NotBlank(message = "id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 参数名称
     */
    @ApiModelProperty("参数名称")
    @NotBlank(message = "参数名称不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String name;
    /**
     * 参数键名
     */
    @ApiModelProperty("参数键名")
    @NotBlank(message = "参数键名不能为空",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String paramLable;
    /**
     * 参数键值
     */
    @ApiModelProperty("参数键值")
    @NotBlank(message = "参数键值不能为空",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String paramValue;
    /**
     * 是否启用(0:否 1:是)
     */
    @ApiModelProperty("是否启用(0:否 1:是)")
    @NotNull(message = "参数名称不能为空",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private IsEnableEnum isEnable;

    
    
}
