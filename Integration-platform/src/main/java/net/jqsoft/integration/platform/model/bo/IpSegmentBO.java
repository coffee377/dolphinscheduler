package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.common.validate.ValidationGroups;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("添加IP白名单入参")
public class IpSegmentBO implements Serializable {
    
    @NotBlank(message = "id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private String id;
    /**
     * IP类型
     */
    @ApiModelProperty("IP类型")
    @NotBlank(message = "IP类型", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer ipType;
    /**
     * IP地址
     */
    @ApiModelProperty("IP地址")
    @NotBlank(message = "IP地址不能为空",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String ipSegment;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String userId;

    
    
}
