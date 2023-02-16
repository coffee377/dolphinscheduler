package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
@ApiModel("数据源信息分页查询入参")
public class DsDriverInfoQueryBO extends BaseQueryParams {
    private String dsType;

}
