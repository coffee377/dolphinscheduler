package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.model.bo.IpSegmentQueryBO;
import net.jqsoft.integration.platform.model.bo.TaskInstanceBO;
import net.jqsoft.integration.platform.model.bo.TaskInstanceQueryBO;
import net.jqsoft.integration.platform.model.entity.TaskInstance;
import net.jqsoft.integration.platform.model.vo.TaskInstanceVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.jqsoft.integration.platform.util.PageInfo;


import java.util.List;
import java.util.Map;

public interface TaskInstanceService extends BaseService<TaskInstance> {

    PageInfo<Map<String, Object>> queryListByPage(TaskInstanceQueryBO req);
}
