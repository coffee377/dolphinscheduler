package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jqsoft.integration.platform.base.BaseEntity;

import java.io.Serializable;

@Data
@TableName("t_dip_ds_driver_info")
@Accessors(chain = true)
public class DsDriverInfo extends BaseEntity implements Serializable {
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
   
    
}
