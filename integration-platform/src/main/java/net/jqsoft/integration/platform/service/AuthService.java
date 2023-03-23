package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.LoginBO;

public interface AuthService {
    CommonResult<String> authLogin(LoginBO req);
}
