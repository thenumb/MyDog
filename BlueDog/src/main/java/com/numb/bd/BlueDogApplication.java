/* Copyright © 2020 你哭时梨花带雨。 and/or its affiliates. All rights reserved. */
package com.numb.bd;

import com.numb.cd.common.utils.ConfigurationLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author thenumb
 * @date 2020-11-27 12:28 上午
 */
@SpringBootApplication
@ComponentScan(basePackages = {""})
public class BlueDogApplication {
    /**
     * 启动类
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurationLoader.initConfigurationLoader();
        SpringApplication.run(BlueDogApplication.class, args);
    }
}