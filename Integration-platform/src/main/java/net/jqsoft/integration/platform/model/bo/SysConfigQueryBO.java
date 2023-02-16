package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
@ApiModel("系统参数配置分页查询入参")
public class SysConfigQueryBO extends BaseQueryParams {
    
    private String paramName;
}
