package net.jqsoft.integration.platform.service.impl;

import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.SysConfigMapper;
import net.jqsoft.integration.platform.mapstruct.SysConfigMapStruct;
import net.jqsoft.integration.platform.model.bo.SysConfigBO;
import net.jqsoft.integration.platform.model.entity.SysConfig;
import net.jqsoft.integration.platform.service.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    
    @Resource
    private SysConfigMapStruct sysConfigMapStruct;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysConfigBO req) {
        SysConfig config = sysConfigMapStruct.toEntity(req);
        this.baseMapper.insert(config);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysConfig(SysConfigBO req) {
        SysConfig config = sysConfigMapStruct.toEntity(req);
        this.baseMapper.updateById(config);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigById(String id) {
        this.baseMapper.deleteById(id);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigBatch(List<String> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }
}
