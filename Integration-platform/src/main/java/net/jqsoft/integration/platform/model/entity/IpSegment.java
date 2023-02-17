package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_dip_network")
public class IpSegment  implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    /**
     * IP地址
     */
    private String ipSegment;
    /**
     * IP类型
     */
    private Integer ipType;

    /**
     * IP类型
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;



}
    

