package net.jqsoft.integration.platform.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.0.1
 */
@Data
@ConfigurationProperties(prefix = "dolphinscheduler")
public class DolphinSchedulerProperties {

    /**
     * 是否启用海豚 RestTemplate 客户端
     */
    private Boolean enabled;

    /**
     * 海豚调度 rootUri
     */
    private String rootUri;

    /**
     * 海豚调度令牌
     */
    private String token;
}
