package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.bo.DsxJobBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsJob;

import java.util.List;

public interface DsxJobService extends BaseService<DxsJob> {
    void insert(DsxJobBO req);
    
    void update(DsxJobBO req);
    
    void deleteConfigById(Long id);
    
    void deleteConfigBatch(List<Long> ids);
    
}
