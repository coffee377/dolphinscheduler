package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_sys_config")
@Accessors(chain = true)
public class DsDriverInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 驱动程序类名
     */
    private String driverName;
    /**
     * 驱动地址
     */
    private String driverUrl;
    /**
     * 数据库类型(如MYSQL,H2,SQLSERVER等)
     */
    private String dsType;
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
