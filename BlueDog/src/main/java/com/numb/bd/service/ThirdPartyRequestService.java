package com.numb.bd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ThirdPartyRequestService {
    @Autowired
    private RestTemplate thirdPartyRequestRestTemplate;

    /**
     * 自定义请求头和请求体的POST请求调用方式
     *
     * @param url           请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType  返回对象类型
     * @param uriVariables  URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> postRequest(String url, HttpEntity<?> requestEntity, Class<T> responseType,
                                             Map<String, ?> uriVariables) {
        ResponseEntity<T> exchange;
        try {
            exchange = thirdPartyRequestRestTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
        } catch (RestClientException e) {
            System.out.println("请求失败");
            return null;
        }
        if (200 == exchange.getStatusCode().value()) {
            System.out.println("请求成功。返回200");
            return exchange;
        }
        return exchange;
    }
}
