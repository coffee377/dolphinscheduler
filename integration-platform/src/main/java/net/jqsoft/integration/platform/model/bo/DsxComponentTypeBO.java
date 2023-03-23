package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("组件类型名信息入参")
public class DsxComponentTypeBO  extends BaseQueryParams  implements Serializable {
    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 标题
     */
    @NotBlank(message = "组件类型key能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "组件类型key",required = true)
    private String keyId;
    @NotBlank(message = "组件类型名不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "组件类型名",required = true)
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "序号")
    private String sort;


    
}
