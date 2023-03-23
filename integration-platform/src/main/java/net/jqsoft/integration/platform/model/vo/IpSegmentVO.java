package net.jqsoft.integration.platform.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_ds_network")
public class IpSegmentVO  implements Serializable {
    /**
     * 主键id
     */
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
    

