package org.apache.dolphinscheduler.api.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据源表信息controller")
@RestController
@RequestMapping("/tableInfos")
public class TableInfosController  extends BaseController{
}
