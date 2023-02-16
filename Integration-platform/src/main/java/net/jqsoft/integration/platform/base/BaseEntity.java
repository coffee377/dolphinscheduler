package net.jqsoft.integration.platform.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 创建时间
     */
    private Date createdTime;
    
    
    /**
     * 创建人Id
     */
    private String createdId;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 更新时间
     */
    private Date updatedTime;
    
    /**
     * 更新人Id
     */
    private String updatedId;
    
    /**
     * 更新人
     */
    private String updatedUser;

}
