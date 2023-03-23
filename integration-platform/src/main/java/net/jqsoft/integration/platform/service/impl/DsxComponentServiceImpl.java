package net.jqsoft.integration.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapper.DsxComponentMapper;
import net.jqsoft.integration.platform.mapper.DsxDhdCategoryMapper;
import net.jqsoft.integration.platform.mapstruct.DsxDndRegisterMapStruct;
import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
import net.jqsoft.integration.platform.model.enums.Status;
import net.jqsoft.integration.platform.service.DsxComponentTypeService;
import net.jqsoft.integration.platform.service.DsxDhdComponentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DsxComponentServiceImpl extends BaseServiceImpl<DsxComponentMapper, DxsComponentType> implements DsxComponentTypeService {
    
    @Resource
    private DsxDndRegisterMapStruct dsxDndRegisterMapStruct;

    private DsxComponentMapper dsxComponentMapper;

    @Resource
    private DsxDhdComponentService dsxDhdComponentService;
    private static  final  int firstSort = 1;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DsxComponentTypeBO req) {
        DxsComponentType dxsComponentType = dsxDndRegisterMapStruct.toEntity(req);
        dxsComponentType.setSort(getMaxSort());
        this.baseMapper.insert(dxsComponentType);
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DsxComponentTypeBO req) {
        DxsComponentType dxsComponentType = dsxDndRegisterMapStruct.toEntity(req);
        this.baseMapper.updateById(dxsComponentType);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteConfigById(Long id) {
        CommonResult result = new CommonResult();
        QueryWrapper<DxsDhdComponent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq( "registry_id", id);
        List<DxsDhdComponent> dxsDhdComponent = dsxDhdComponentService.list(queryWrapper);
        if(CollectionUtils.isEmpty(dxsDhdComponent)){
            this.baseMapper.deleteById(id);
            return CommonResult.success();
        }else{
            result.setStatus(Status.REGISTRY_COMPONENT_EXIST.getCode());
            result.setMsg(Status.REGISTRY_COMPONENT_EXIST.getMsg());
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
            Integer sort = this.dsxComponentMapper.selectMaxSort();
            return this.dsxComponentMapper.selectMaxSort() +firstSort;
        }else{
            return firstSort;
        }
    };

}
