package net.jqsoft.integration.platform.mapstruct;

import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.vo.DsDriverInfoVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * <p>
 * 系统参数配置信息表 Mapper 实体映射
 * </p>
 *
 * @author yuwei
 * @since 2020-05-19
 */
@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface DsDriverInfoMapStruct extends EntityMapper<DsDriverInfoBO, DsDriverInfo, DsDriverInfoVO> {

}
