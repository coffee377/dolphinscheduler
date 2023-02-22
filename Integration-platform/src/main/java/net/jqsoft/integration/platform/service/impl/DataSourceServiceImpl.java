package net.jqsoft.integration.platform.service.impl;

import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.DataSourceMapper;
import net.jqsoft.integration.platform.mapper.DsDriverInfoMapper;
import net.jqsoft.integration.platform.mapstruct.DsDriverInfoMapStruct;
import net.jqsoft.integration.platform.model.bo.DsDriverInfoBO;
import net.jqsoft.integration.platform.model.entity.DataSource;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.service.DataSourceService;
import net.jqsoft.integration.platform.service.DsDriverInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataSourceServiceImpl extends BaseServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {
    

}
