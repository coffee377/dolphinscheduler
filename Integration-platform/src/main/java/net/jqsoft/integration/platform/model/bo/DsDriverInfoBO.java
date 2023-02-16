package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.validate.ValidationGroups;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class DsDriverInfoBO implements Serializable {
    /**
     * 主键id
     */
    @NotBlank(message = "主键id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty("标题")
    private String title;
    /**
     * 驱动程序类名
     */
    @NotBlank(message = "驱动程序类名不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty("驱动程序类名")
    private String driverName;
    /**
     * 驱动地址
     */
    @NotBlank(message = "驱动地址不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty("驱动地址")
    private String driverUrl;
    /**
     * 数据库类型(如MYSQL,H2,SQLSERVER等)
     */
    @NotBlank(message = "数据库类型不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(" 数据库类型(如MYSQL,H2,SQLSERVER等)")
    private String dsType;
    
}
