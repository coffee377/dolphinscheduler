package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.model.bo.IpSegmentBO;
import net.jqsoft.integration.platform.model.entity.IpSegment;

import java.util.List;

public interface IpSegmentService extends BaseService<IpSegment> {
    void insert(IpSegmentBO req);
    
    void updateIpSegment(IpSegmentBO req);
    
    void deleteIpSegmentById(String id);
    
    void deleteIpSegmentBatch(List<String> ids);
}
