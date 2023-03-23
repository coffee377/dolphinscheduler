package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("DHD分类信息入参")
public class DsxDhdCategoryBO extends BaseQueryParams implements Serializable {
    @ApiModelProperty("主键id")
    private int id;


    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父节点ID")
    private Long pid;

    @ApiModelProperty("序号")
    private int sort;


    @ApiModelProperty("配置参数")
    private String configuration;



}
