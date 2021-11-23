/* Copyright © 2020 你哭时梨花带雨。 and/or its affiliates. All rights reserved. */
package com.numb.rd.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thenumb
 * @date 2020-11-27 12:30 上午
 */
@RestController
@RequestMapping("/test1")
public class RedController {
    @PostMapping("/test")
    public JSONObject test1(@RequestBody TestBean testBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "12");
        jsonObject.put("name", "boy");
        return jsonObject;
    }
}
