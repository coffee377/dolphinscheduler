package net.jqsoft.integration.platform.model.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("统一认证接口入参")
public class LoginBO implements Serializable {
    
    
    @NotBlank(message = "token端点url不能为空")
    private String token_endpoint_uri;
    
    @NotBlank(message = "授权码不能为空")
    private String code;
    
    @NotBlank(message = "重定向地址不能为空")
    private String redirect_uri;
    
    @NotBlank(message = "客户端id不能为空")
    private String client_id;
    
    @NotBlank(message = "校验码不能为空")
    private String code_verifier;
    
}
