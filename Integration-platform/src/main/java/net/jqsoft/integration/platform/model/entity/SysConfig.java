package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_sys_config")
public class SysConfig  implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
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
    private Integer isEnable;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 创建人id
     */
    private String createdId;
    /**
     * 创建人姓名
     */
    private String createdUser;
    /**
     * 修改人id
     */
    private String updatedId;
    /**
     * 修改人姓名
     */
    private String updatedUser;
    /**
     * 修改时间
     */
    private Date updatedTime;
    
    
}
