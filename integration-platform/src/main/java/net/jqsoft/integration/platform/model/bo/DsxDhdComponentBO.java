package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("DHD组件入参")
public class DsxDhdComponentBO extends BaseQueryParams implements Serializable {
    @NotNull(message = "id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private int id;

    @NotBlank(message = "组件名称不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "组件名称", required = true)
    private String name;

    @ApiModelProperty(value = "组件图标")
    private String icon;

    @ApiModelProperty(value = "图标类型")
    private String type;

    @NotNull(message = "分类ID不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "分类ID", required = true)
    private Integer cid;

    @ApiModelProperty(value = "配置参数")
    private String configuration;

    @ApiModelProperty(value = "注册主键ID")
    private Integer registryId;
    @ApiModelProperty(value = "是否可扩展(1:是，0：否)")
    private Integer refinement;
    @ApiModelProperty(value = "序号")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String description;
}
