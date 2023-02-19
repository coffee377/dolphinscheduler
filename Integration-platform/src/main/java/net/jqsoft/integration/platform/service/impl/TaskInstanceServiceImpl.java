package net.jqsoft.integration.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.mapper.TaskInstanceMapper;
import net.jqsoft.integration.platform.mapstruct.TaskInstanceMapStruct;
import net.jqsoft.integration.platform.model.bo.TaskInstanceQueryBO;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import net.jqsoft.integration.platform.model.vo.TaskInstanceVO;
import net.jqsoft.integration.platform.service.TaskInstanceService;
import net.jqsoft.integration.platform.util.CollectionUtils;
import net.jqsoft.integration.platform.util.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskInstanceServiceImpl extends BaseServiceImpl<TaskInstanceMapper, TaskInstance> implements TaskInstanceService {
    
    @Resource
    private TaskInstanceMapStruct taskInstanceMapStruct;

    @Resource
    private TaskInstanceService taskInstanceService;

    @Resource
    private  TaskInstanceMapper taskInstanceMapper;


    @Override
    public PageInfo<Map<String, Object>> queryListByPage(TaskInstanceQueryBO req) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectName",req.getProjectName());
        params.put("processInstanceName",req.getProcessInstanceName());
        params.put("taskInstanceName",req.getTaskInstanceName());

        Page<TaskInstance> page = new Page<>(req.getPageNum(), req.getPageSize());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(req.getPageNum(), req.getPageSize());
        IPage<TaskInstance> taskInstanceIPage = taskInstanceMapper.queryTaskInstanceListPaging(
                page, req.getProjectName(),req.getProcessInstanceName(),req.getTaskInstanceName()
        );
        Set<String> exclusionSet = new HashSet<>();
        exclusionSet.add("class");
        exclusionSet.add("taskJson");
        List<TaskInstanceVO> collect = taskInstanceIPage.getRecords().stream().map(taskInstanceMapStruct::toVO).collect(Collectors.toList());
        pageInfo.setTotal((int) taskInstanceIPage.getTotal());
        pageInfo.setTotalList(CollectionUtils.getListByExclusion(taskInstanceIPage.getRecords(), exclusionSet));

         return  pageInfo;
    }
}
