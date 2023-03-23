package net.jqsoft.integration.platform.mapstruct;

import net.jqsoft.integration.platform.model.bo.TaskInstanceBO;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import net.jqsoft.integration.platform.model.vo.TaskInstanceVO;
import org.mapstruct.Mapper;

/**
 * <p>
 * 任务实例 Mapper 实体映射
 * </p>
 *
 * @author yuwei
 * @since 2020-05-19
 */
@Mapper(componentModel = "spring")
public interface TaskInstanceMapStruct extends EntityMapper<TaskInstanceBO, TaskInstance, TaskInstanceVO> {

}
