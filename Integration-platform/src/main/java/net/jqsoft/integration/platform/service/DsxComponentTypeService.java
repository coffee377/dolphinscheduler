package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;

import java.util.List;

public interface DsxComponentTypeService extends BaseService<DxsComponentType> {
    void insert(DsxComponentTypeBO req);
    
    void update(DsxComponentTypeBO req);

    CommonResult deleteConfigById(Long id);
    
    void deleteConfigBatch(List<Long> ids);
    
}
