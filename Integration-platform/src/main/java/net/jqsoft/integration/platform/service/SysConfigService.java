package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.model.bo.SysConfigBO;
import net.jqsoft.integration.platform.model.entity.SysConfig;

import java.util.List;

public interface SysConfigService extends BaseService<SysConfig> {
    void insert(SysConfigBO req);
    
    void updateSysConfig(SysConfigBO req);
    
    void deleteConfigById(String id);
    
    void deleteConfigBatch(List<String> ids);
}
