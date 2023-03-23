package net.jqsoft.integration.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapper.DsxDhdComponentMapper;
import net.jqsoft.integration.platform.mapstruct.DsxDhDComponentMapStruct;
import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsDhdCategory;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
import net.jqsoft.integration.platform.model.enums.Status;
import net.jqsoft.integration.platform.service.DsxComponentTypeService;
import net.jqsoft.integration.platform.service.DsxDhdCategoryService;
import net.jqsoft.integration.platform.service.DsxDhdComponentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

@Service
public class DsxDhdComponentServiceImpl extends BaseServiceImpl<DsxDhdComponentMapper, DxsDhdComponent> implements DsxDhdComponentService {

    private static  final  int firstSort = 1;
    @Resource
    private DsxDhDComponentMapStruct dsxDhDComponentMapStruct;
    @Resource
    private DsxDhdComponentMapper dsxDhdComponentMapper;

    @Resource
    private DsxDhdCategoryService dsxDhdCategoryService;

    @Resource
    private DsxComponentTypeService dsxComponentTypeService;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult insert(DsxDhdComponentBO req) {
        DxsDhdComponent dxsDhdComponent = dsxDhDComponentMapStruct.toEntity(req);
        dxsDhdComponent.setSort(getMaxSort());
        CommonResult result = checkParams(dxsDhdComponent);
        if(result.getStatus() != null){
            return result;
        }
        this.baseMapper.insert(dxsDhdComponent);
        return CommonResult.success();

    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult update(DsxDhdComponentBO req) {
        DxsDhdComponent dxsDhdComponent = dsxDhDComponentMapStruct.toEntity(req);
        CommonResult result = checkParams(dxsDhdComponent);
        if(result.getStatus() != null){
            return result;
        }
        this.baseMapper.updateById(dxsDhdComponent);
        return CommonResult.success();
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


    public int getMaxSort(){
        Integer count = this.baseMapper.selectCount(new QueryWrapper<>());
        if(count > 0){
            return this.dsxDhdComponentMapper.selectMaxSort() +firstSort;
        }else{
            return firstSort;
        }
    };


    public CommonResult checkParams(DxsDhdComponent dxsDhdComponent) {
        CommonResult result = new CommonResult();
        //查询父节点是否存在分类
        DxsDhdCategory dxsDhdCategory = dsxDhdCategoryService.getById(dxsDhdComponent.getCid());
        if (dxsDhdCategory == null) {
            result.setStatus(Status.PARENT_RESOURCE_NOT_EXIST.getCode());
            result.setMsg(Status.PARENT_RESOURCE_NOT_EXIST.getMsg());
        }
        //判断注册组件是否存在
        DxsComponentType dxsComponentType = dsxComponentTypeService.getById(dxsDhdComponent.getRegistryId());
        if (dxsComponentType == null) {
            result.setStatus(Status.RESISTER_NOT_EXIST.getCode());
            result.setMsg(Status.RESISTER_NOT_EXIST.getMsg());
        }
        return result;
    }
}
