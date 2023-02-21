package org.apache.dolphinscheduler.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("字段实体出参")
public class DataSourceTableColumnVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
     * 表id
     */
    @ApiModelProperty("表名称")
    private Integer tableName;
    /**
     * 字段名称
     */
    @ApiModelProperty("字段名称")
    private String columnName;
    /**
     * 字段大小
     */
    @ApiModelProperty("字段大小")
    private String columnSize;
    /**
     * java.sql.Types 的 SQL 类型
     */
    @ApiModelProperty("java.sql.Types 的 SQL 类型")
    private String dataType;
    /**
     * 小数位数
     */
    @ApiModelProperty("小数位数")
    private String decimalDigits;
    /**
     * 是否自增
     */
    @ApiModelProperty("是否自增")
    private String isAutoincrement;
    /**
     * 是否允许为null值
     */
    @ApiModelProperty("是否允许为null值")
    private String isNullable;
    /**
     * 字段注释
     */
    @ApiModelProperty("字段注释")
    private String remarks;
    /**
     * 字段默认值
     */
    @ApiModelProperty("字段默认值")
    private String columnDef;
    
    /**
     * 字段类型名称
     */
    @ApiModelProperty("字段类型名称")
    private String typeName;
    /**
     * 数据源id
     */
    @ApiModelProperty("数据源id")
    private String dataSourceId;
    
}
