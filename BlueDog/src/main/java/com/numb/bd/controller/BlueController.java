/* Copyright © 2020 你哭时梨花带雨。 and/or its affiliates. All rights reserved. */
package com.numb.bd.controller;

import com.numb.bd.bean.TestBean;
import com.numb.bd.service.ThirdPartyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author thenumb
 * @date 2020-11-27 12:28 上午
 */
@RestController
@RequestMapping("/test")
public class BlueController {
    @Autowired
    ThirdPartyRequestService thirdPartyRequestService;

    @PostMapping("test2")
    public TestBean test(@RequestBody TestBean testBean) {
        System.out.println(testBean.getId());
        System.out.println(testBean.getName());
        TestBean requestTestBean = new TestBean();
        requestTestBean.setId("1");
        requestTestBean.setId("cc");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestBean> entity = new HttpEntity<>(requestTestBean, httpHeaders);
        ResponseEntity<TestBean> responseEntity = thirdPartyRequestService.postRequest("http://127.0.0.1:8089/test1/test", entity, TestBean.class, new HashMap<>());
        TestBean body = responseEntity.getBody();
        return body;
    }
}
