package net.jqsoft.integration.platform.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 返回码  <br/>200-成功  <br/>201-失败
     */
    private Integer status;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回业务数据
     */
    private T data;

    public CommonResult(Integer status, String msg) {
        this(status, msg, null);
    }

    //成功
    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(RequestCode.success, "成功", data);
    }

    //失败
    public static <T> CommonResult<T> failed() {
        return failed(RequestCode.success_created, "失败");
    }

    public static <T> CommonResult<T> failed(String msg) {
        return failed(RequestCode.success_created, msg);
    }

    public static <T> CommonResult<T> paramError(BindingResult result) {
        return failed(RequestCode.syntax_error, result.getFieldError().getDefaultMessage());
    }

    public static <T> CommonResult<T> failed(Integer status, String msg) {
        return new CommonResult<>(status, msg, null);
    }

    public CommonResult status(Integer status) {
        this.status = status;
        return this;
    }

    public CommonResult msg(String msg) {
        this.msg = msg;
        return this;
    }

    public CommonResult data(T data) {
        this.data = data;
        return this;
    }

    public Boolean isSuccess() {
        return this.status == RequestCode.success;
    }

}
