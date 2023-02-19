package net.jqsoft.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskInstanceMapper extends BaseMapper<TaskInstance> {

    IPage<TaskInstance> queryTaskInstanceListPaging(IPage<TaskInstance> page,
                                                    @Param("projectName") String projectCode,
                                                    @Param("processInstanceName") String processInstanceName,
                                                    @Param("taskInstanceName") String taskInstanceName

    );
}
