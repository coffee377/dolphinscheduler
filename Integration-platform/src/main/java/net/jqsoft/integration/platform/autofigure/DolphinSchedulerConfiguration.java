package net.jqsoft.integration.platform.autofigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.0.1
 */
@Configuration
@EnableConfigurationProperties(DolphinSchedulerProperties.class)
@ConditionalOnProperty(prefix = "dolphinscheduler", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DolphinSchedulerConfiguration {

    @Bean
    RestTemplate dsClient(RestTemplateBuilder builder, DolphinSchedulerProperties ds) {
        String rootUri = ds.getRootUri();
        String token = ds.getToken();
        Assert.hasText(rootUri, "dolphinscheduler.rootUri must not be null");
        Assert.hasText(token, "dolphinscheduler.token must not be null");
        return builder
                .rootUri(rootUri)
                .defaultHeader("token", token)
                .build();
    }

}
