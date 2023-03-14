package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;

import java.util.List;

public interface DsxDhdComponentService extends BaseService<DxsDhdComponent> {


    CommonResult insert(DsxDhdComponentBO req);
    void deleteConfigById(Long id);

    void deleteConfigBatch(List<Long> ids);
    CommonResult update(DsxDhdComponentBO req);

    
}
