package net.jqsoft.integration.platform.service.impl;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapper.DsxComponentMapper;
import net.jqsoft.integration.platform.mapper.DsxDhdCategoryMapper;
import net.jqsoft.integration.platform.mapstruct.DsxDhDCategoryMapStruct;
import net.jqsoft.integration.platform.mapstruct.DsxDndRegisterMapStruct;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.bo.DsxDhdCategoryBO;
import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsDhdCategory;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
import net.jqsoft.integration.platform.model.enums.Status;
import net.jqsoft.integration.platform.service.DsxComponentTypeService;
import net.jqsoft.integration.platform.service.DsxDhdCategoryService;
import net.jqsoft.integration.platform.service.DsxDhdComponentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DsxDhdCategoryServiceImpl extends BaseServiceImpl<DsxDhdCategoryMapper, DxsDhdCategory> implements DsxDhdCategoryService {
    
    @Resource
    private DsxDhDCategoryMapStruct dsxDhDCategoryMapStruct;

    private DsxDhdCategoryMapper categoryMapper;

    @Resource
    private DsxDhdComponentService dsxDhdComponentService;

    private static  final  int firstSort = 1;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DsxDhdCategoryBO req) {
        DxsDhdCategory dhdCategory = dsxDhDCategoryMapStruct.toEntity(req);
        dhdCategory.setSort(getMaxSort());
        this.baseMapper.insert(dhdCategory);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DsxDhdCategoryBO req) {
        DxsDhdCategory dhdCategory = dsxDhDCategoryMapStruct.toEntity(req);
        this.baseMapper.updateById(dhdCategory);
    }

    @Override
    public Map<DxsDhdCategory, Object> getTree() {
        Map<DxsDhdCategory, Object> result = new HashMap<>();
        List<DxsDhdCategory> dxsDhdCategories = baseMapper.selectList(new QueryWrapper<>());
        if(CollectionUtils.isEmpty(dxsDhdCategories)){
            return result;
        }
        dxsDhdCategories.forEach(dxsDhdCategory -> {
            QueryWrapper<DxsDhdComponent> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cid", dxsDhdCategory.getId());
            List<DxsDhdComponent> componentList = dsxDhdComponentService.list(queryWrapper);
            result.put(dxsDhdCategory , componentList);
        });
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteConfigById(Long id) {
        CommonResult result = new CommonResult();
        QueryWrapper<DxsDhdComponent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq( "cid", id);
        List<DxsDhdComponent> dxsDhdComponentList = dsxDhdComponentService.list(queryWrapper);
        if(CollectionUtils.isEmpty(dxsDhdComponentList)){
            this.baseMapper.deleteById(id);
            return CommonResult.success();
        }else{
            result.setStatus(Status.RESOURCE_COMPONENT_EXIST.getCode());
            result.setMsg(Status.RESOURCE_COMPONENT_EXIST.getMsg());
            return result;
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigBatch(List<Long> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }

    public int getMaxSort(){
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        if(count > 0){
            Integer sort = this.categoryMapper.selectMaxSort();
            return this.categoryMapper.selectMaxSort() +firstSort;
        }else{
            return firstSort;
        }
    };
}
