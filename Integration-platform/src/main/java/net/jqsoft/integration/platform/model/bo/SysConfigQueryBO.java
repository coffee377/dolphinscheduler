package net.jqsoft.integration.platform.model.bo;

import lombok.Data;
import net.jqsoft.integration.platform.base.BaseQueryParams;

@Data
public class SysConfigQueryBO extends BaseQueryParams {
    
    private String paramName;
}
