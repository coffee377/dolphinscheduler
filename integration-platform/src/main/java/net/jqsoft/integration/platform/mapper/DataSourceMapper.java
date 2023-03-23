package net.jqsoft.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.jqsoft.integration.platform.model.entity.DataSource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataSourceMapper extends BaseMapper<DataSource> {
}
