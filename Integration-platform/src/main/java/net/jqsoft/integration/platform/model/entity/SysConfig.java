package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jqsoft.integration.platform.base.BaseEntity;
import net.jqsoft.integration.platform.model.enums.IsEnableEnum;

import java.io.Serializable;

@Data
@TableName("t_dip_sys_config")
@Accessors(chain = true)
public class SysConfig extends BaseEntity implements Serializable {
    /**
     * 主键id
     */
    
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数键名
     */
    private String paramLable;
    /**
     * 参数键值
     */
    private String paramValue;
    /**
     * 是否启用(0:否 1:是)
     */
    private IsEnableEnum isEnable;
  
    
}
