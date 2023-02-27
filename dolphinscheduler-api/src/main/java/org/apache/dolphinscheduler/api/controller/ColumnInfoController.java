package org.apache.dolphinscheduler.api.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据源字段信息controller")
@RestController
@RequestMapping("/columnInfo")
public class ColumnInfoController extends BaseController{
}
