package net.jqsoft.integration.platform.mapstruct;

import net.jqsoft.integration.platform.model.bo.IpSegmentBO;
import net.jqsoft.integration.platform.model.entity.IpSegment;
import net.jqsoft.integration.platform.model.vo.IpSegmentVO;
import org.mapstruct.Mapper;

/**
 * <p>
 * 系统参数配置信息表 Mapper 实体映射
 * </p>
 *
 * @author yuwei
 * @since 2020-05-19
 */
@Mapper(componentModel = "spring")
public interface IpSegmentMapStruct extends EntityMapper<IpSegmentBO, IpSegment, IpSegmentVO> {

}
