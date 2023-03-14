package net.jqsoft.integration.platform.service.impl;

import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.DsxJobMapper;
import net.jqsoft.integration.platform.mapstruct.DsxJobMapStruct;
import net.jqsoft.integration.platform.model.bo.DsxJobBO;
import net.jqsoft.integration.platform.model.entity.DxsJob;
import net.jqsoft.integration.platform.service.DsxJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DsxJobServiceImpl extends BaseServiceImpl<DsxJobMapper, DxsJob> implements DsxJobService {
    
    @Resource
    private DsxJobMapStruct dsxJobMapStruct;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DsxJobBO req) {
        DxsJob dsxJobBO = dsxJobMapStruct.toEntity(req);
        dsxJobBO.setContentHash(dsxJobBO.hashCode());
        this.baseMapper.insert(dsxJobBO);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DsxJobBO req) {
        DxsJob dsxJobBO = dsxJobMapStruct.toEntity(req);
        dsxJobBO.setContentHash(dsxJobBO.hashCode());
        this.baseMapper.updateById(dsxJobBO);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigById(Long id) {
        this.baseMapper.deleteById(id);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigBatch(List<Long> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }
}
