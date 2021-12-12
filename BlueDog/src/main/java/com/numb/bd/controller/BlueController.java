/* Copyright © 2020 你哭时梨花带雨。 and/or its affiliates. All rights reserved. */
package com.numb.bd.controller;

import com.numb.cd.common.utils.ConfigurationLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thenumb
 * @date 2020-11-27 12:28 上午
 */
@RestController
@RequestMapping("/test")
public class BlueController {
    @GetMapping("test2")
    public String test() {
        return ConfigurationLoader.getString("huya", "sss");
    }
}
