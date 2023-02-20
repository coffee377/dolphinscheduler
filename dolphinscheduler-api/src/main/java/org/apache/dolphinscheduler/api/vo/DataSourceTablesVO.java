package org.apache.dolphinscheduler.api.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class DataSourceTablesVO implements Serializable {
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
    
}
