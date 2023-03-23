package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;

import java.util.List;

public interface DsDriverInfoService extends BaseService<DsDriverInfo> {
    void insert(DsDriverInfoBO req);
    
    void updatedDriverInfo(DsDriverInfoBO req);
    
    void deleteConfigById(String id);
    
    void deleteConfigBatch(List<String> ids);
    
}
