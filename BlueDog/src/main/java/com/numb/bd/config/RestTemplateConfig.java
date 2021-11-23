package com.numb.bd.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateProperties restTemplateProperties;


    @Bean("thirdPartyRequestRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());

    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

        //整个连接池最大连接数
        connectionManager.setMaxTotal(NumberUtils.toInt(restTemplateProperties.getMaxTotal(), 100));

        //连接同时并发请求数
        connectionManager.setDefaultMaxPerRoute(NumberUtils.toInt(restTemplateProperties.getDefaultMaxPerRoute(), 100));
        RequestConfig requestConfig = RequestConfig.custom()

                //服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setSocketTimeout(NumberUtils.toInt(restTemplateProperties.getSocketTimeout(), 6000))

                //连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectTimeout(NumberUtils.toInt(restTemplateProperties.getConnectTimeout(), 6000))

                //从连接池中获取连接的超时时间，超过该时间未拿到可用连接，抛出ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .setConnectionRequestTimeout(NumberUtils.toInt(restTemplateProperties.getConnectionRequestTimeout(), 6000))
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
