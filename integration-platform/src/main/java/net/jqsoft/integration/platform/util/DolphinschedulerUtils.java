package net.jqsoft.integration.platform.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zjl
 * @Date: 2023/1/6 09:56
 * @Description: 海豚调度工具类
 */
@Slf4j
public class DolphinschedulerUtils {



    public static void getDataReport(String postUrl, String token , RestTemplate restTemplate){
        //调用海豚工具执行下发任务
        HttpHeaders headers = new HttpHeaders();
        // 设置验签用的数据
        headers.add("token", token);
        // 设置content-type,很据需求设置
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 设置请求体
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(postUrl, request, String.class);
        log.info("响应报文-->" + response);
    }

}
