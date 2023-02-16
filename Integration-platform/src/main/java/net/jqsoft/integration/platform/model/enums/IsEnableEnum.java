package net.jqsoft.integration.platform.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author gzp
 * @date 2022/9/16 14:25
 */
public enum IsEnableEnum {
    NO(0, "未启用"),
    YES(1, "启用");
    
    @EnumValue
    private final Integer value;
    
    @JsonValue
    private final String display;
    
    IsEnableEnum(Integer value, String display) {
        this.value = value;
        this.display = display;
    }
}
