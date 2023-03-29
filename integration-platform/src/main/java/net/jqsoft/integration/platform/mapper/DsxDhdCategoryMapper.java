package net.jqsoft.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.jqsoft.integration.platform.model.entity.DxsDhdCategory;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DsxDhdCategoryMapper extends BaseMapper<DxsDhdCategory> {
    Integer selectMaxSort();
}