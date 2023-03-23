package net.jqsoft.integration.platform.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.LoginBO;
import net.jqsoft.integration.platform.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {
    
    private final String GRANT_TYPE = "authorization_code";
    @Value("${dolphinscheduler.root-uri}")
    private String url;
    
    @Override
    public CommonResult<String> authLogin(LoginBO req) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", req.getCode());
        map.put("redirect_uri", req.getRedirect_uri());
        map.put("client_id", req.getClient_id());
        map.put("code_verifier", req.getCode_verifier());
        map.put("grant_type", GRANT_TYPE);
        String httpResult = HttpUtil.post(req.getToken_endpoint_uri(), map);
        JSONObject jsonObject = JSONUtil.parseObj(httpResult);
        String idToken = jsonObject.getStr("id_token");
        JWTPayload payload = JWTUtil.parseToken(idToken).getPayload();
        String userName = payload.getClaim("preferred_username").toString();
        if (StringUtils.isBlank(userName)) {
            return CommonResult.failed("该用户未携带用户名信息");
        }
        //此处调海豚登录接口,此处之后用枚举
        url = url + "/dolphinscheduler/loginByUserName";
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("username", userName);
        String dsStr = HttpUtil.post(url, dsMap);
        JSONObject dsJson = JSONUtil.parseObj(dsStr);
        if (dsJson.getInt("code") != 0) {
            return CommonResult.failed("获取子系统token失败");
        }
        String data = dsJson.getStr("data");
        return CommonResult.success(data);
    }
}
