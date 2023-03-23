package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
@ApiModel("数据源信息分页查询入参")
public class DsDriverInfoQueryBO extends BaseQueryParams {
    
    @ApiModelProperty("数据源类型(MYSQL,H2,SQLSERVER等)")
    private String dsType;

}
