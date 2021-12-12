package com.numb.cd.common.config.resttemplate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rest.template.config.prefix")
@Configuration
@Data
public class RestTemplateProperties {
    //最大连接数
    private String maxTotal;

    //读取超时
    private String socketTimeout;

    //连接超时
    private String connectTimeout;

    //获取连接超时
    private String connectionRequestTimeout;

    //连接并发数
    private String defaultMaxPerRoute;
}
