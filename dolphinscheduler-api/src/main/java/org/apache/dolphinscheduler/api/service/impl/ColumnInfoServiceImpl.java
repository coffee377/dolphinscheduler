package org.apache.dolphinscheduler.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dolphinscheduler.api.service.ColumnInfoSerive;
import org.apache.dolphinscheduler.dao.entity.ColumnInfo;
import org.apache.dolphinscheduler.dao.mapper.ColumnInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ColumnInfoServiceImpl extends ServiceImpl<ColumnInfoMapper, ColumnInfo> implements ColumnInfoSerive {

}
