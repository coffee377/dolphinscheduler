package org.apache.dolphinscheduler.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("t_dsx_ds_table_info")
public class TableInfos implements Serializable {
    
    @TableId(type = IdType.ID_WORKER)
    private Integer id;
    
    private String tableName;
    
    private String tableSchem;
    
    private String tableType;
    
    private String remarks;
    
    private String refGeneration;
    
    private String tableCat;
    
    private String typeCat;
    
    private String typeName;
    
    private String typeSchem;
    
    private Integer dataSourceId;
    
}
