package org.apache.dolphinscheduler.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("t_dip_ds_column_info")
public class ColumnInfo implements Serializable {
    private Integer id;
    
    private String tableName;
    
    private String columnName;
    
    private String columnSize;
    
    private String dataType;
    
    private String decimalDigits;
    
    private String isAutoincrement;
    
    private String isNullable;
    
    private String remarks;
    
    private String columnDef;
    
    private Integer dataSourceId;
    
}
