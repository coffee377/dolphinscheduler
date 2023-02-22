package org.apache.dolphinscheduler.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dolphinscheduler.api.service.TableInfosService;
import org.apache.dolphinscheduler.dao.entity.TableInfos;
import org.apache.dolphinscheduler.dao.mapper.TableInfosMapper;
import org.springframework.stereotype.Service;

@Service
public class TableInfosServiceImpl extends ServiceImpl<TableInfosMapper, TableInfos> implements TableInfosService {

}
