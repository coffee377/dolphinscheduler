package net.jqsoft.integration.platform.service.impl;

import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.IpSegmentMapper;
import net.jqsoft.integration.platform.mapstruct.IpSegmentMapStruct;
import net.jqsoft.integration.platform.model.bo.IpSegmentBO;
import net.jqsoft.integration.platform.model.entity.IpSegment;

import net.jqsoft.integration.platform.service.IpSegmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IpSegmentServiceImpl extends BaseServiceImpl<IpSegmentMapper, IpSegment> implements IpSegmentService {
    
    @Resource
    private IpSegmentMapStruct ipSegmentMapStruct;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(IpSegmentBO req) {
        IpSegment ipSegment = ipSegmentMapStruct.toEntity(req);
        this.baseMapper.insert(ipSegment);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIpSegment(IpSegmentBO req) {
        IpSegment ipSegment = ipSegmentMapStruct.toEntity(req);
        this.baseMapper.updateById(ipSegment);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIpSegmentById(String id) {
        this.baseMapper.deleteById(id);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIpSegmentBatch(List<String> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }
}
