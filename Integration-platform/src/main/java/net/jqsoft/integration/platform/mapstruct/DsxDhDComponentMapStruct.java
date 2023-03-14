package net.jqsoft.integration.platform.mapstruct;

import net.jqsoft.integration.platform.model.bo.DsxDhdComponentBO;
import net.jqsoft.integration.platform.model.entity.DxsDhdComponent;
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
public interface DsxDhDComponentMapStruct extends EntityMapper<DsxDhdComponentBO, DxsDhdComponent, DsDriverInfoVO> {

}
