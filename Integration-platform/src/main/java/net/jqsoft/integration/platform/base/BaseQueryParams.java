package net.jqsoft.integration.platform.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseQueryParams implements Serializable {

    private static final long serialVersionUID = 1L;
    // 当前页码
    private Integer pageNum = 1;
    // 分页条数
    private Integer pageSize = 10;
 
}
