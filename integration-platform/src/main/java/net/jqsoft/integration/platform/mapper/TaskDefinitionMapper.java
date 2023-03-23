package net.jqsoft.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.jqsoft.integration.platform.model.entity.TaskDefinition;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskDefinitionMapper extends BaseMapper<TaskDefinition> {


}
