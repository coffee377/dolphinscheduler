package net.jqsoft.integration.platform.service.impl;

import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.DsDriverInfoMapper;
import net.jqsoft.integration.platform.mapstruct.DsDriverInfoMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.service.DsDriverInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DsDriverInfoServiceImpl extends BaseServiceImpl<DsDriverInfoMapper, DsDriverInfo> implements DsDriverInfoService {
    
    @Resource
    private DsDriverInfoMapStruct dsDriverInfoMapStruct;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DsDriverInfoBO req) {
        DsDriverInfo dsDriverInfo = dsDriverInfoMapStruct.toEntity(req);
        this.baseMapper.insert(dsDriverInfo);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatedDriverInfo(DsDriverInfoBO req) {
        DsDriverInfo dsDriverInfo = dsDriverInfoMapStruct.toEntity(req);
        this.baseMapper.updateById(dsDriverInfo);
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
