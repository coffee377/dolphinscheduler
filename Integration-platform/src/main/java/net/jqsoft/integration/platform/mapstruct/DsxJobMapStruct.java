package net.jqsoft.integration.platform.mapstruct;

import net.jqsoft.integration.platform.model.bo.DsxComponentTypeBO;
import net.jqsoft.integration.platform.model.bo.DsxJobBO;
import net.jqsoft.integration.platform.model.entity.DxsComponentType;
import net.jqsoft.integration.platform.model.entity.DxsJob;
import net.jqsoft.integration.platform.model.vo.DsDriverInfoVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * <p>
 * 作业映射
 * </p>
 *
 * @author yuwei
 * @since 2020-05-19
 */
@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface DsxJobMapStruct extends EntityMapper<DsxJobBO, DxsJob, DsDriverInfoVO> {

}
