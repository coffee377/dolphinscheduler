package net.jqsoft.integration.platform.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DsDriverInfoVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 驱动程序类名
     */
    @ApiModelProperty("驱动程序类名")
    private String driverName;
    /**
     * 驱动地址
     */
    @ApiModelProperty("驱动地址")
    private String driverUrl;
    /**
     * 数据库类型(如MYSQL,H2,SQLSERVER等)
     */
    @ApiModelProperty(" 数据库类型(如MYSQL,H2,SQLSERVER等)")
    private String dsType;
    
}
