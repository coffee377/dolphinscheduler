package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.DsxDhdCategoryBO;
import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsDhdCategory;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;

import java.util.List;
import java.util.Map;

public interface DsxDhdCategoryService extends BaseService<DxsDhdCategory> {


    void insert(DsxDhdCategoryBO req);
    CommonResult deleteConfigById(Long id);

    void deleteConfigBatch(List<Long> ids);
    void update(DsxDhdCategoryBO req);


    Map<DxsDhdCategory,Object> getTree();
}
