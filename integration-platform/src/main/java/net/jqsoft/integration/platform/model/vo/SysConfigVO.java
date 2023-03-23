package net.jqsoft.integration.platform.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.model.enums.IsEnableEnum;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysConfigVO implements Serializable {
    
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 参数名称
     */
    @ApiModelProperty("参数名称")
    private String name;
    /**
     * 参数键名
     */
    @ApiModelProperty("参数键名")
    private String paramLable;
    /**
     * 参数键值
     */
    @ApiModelProperty("参数键值")
    private String paramValue;
    /**
     * 是否启用(0:否 1:是)
     */
    @ApiModelProperty("是否启用(0:否 1:是)")
    private IsEnableEnum isEnable;
    
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    
}
