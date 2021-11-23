package com.numb.bd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rest.template.config.prefix")
@Configuration
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

    public String getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(String socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(String connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public String getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(String defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }
}
