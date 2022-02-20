/* Copyright © 2020 你哭时梨花带雨。 and/or its affiliates. All rights reserved. */
package com.numb.bd.controller;

import com.alibaba.fastjson.JSONObject;
import com.numb.cd.common.utils.ConfigurationLoader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.producer.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Properties;

/**
 * @author thenumb
 * @date 2020-11-27 12:28 上午
 */
@RestController
@RequestMapping("/test")
public class BlueController {
    @GetMapping("test2")
    public String test() {
        Properties props = new Properties();
        //设置kafka集群的地址
        props.put("bootstrap.servers", "numb:9092");
        //ack模式，all是最慢但最安全的
        props.put("acks", "all");
        //失败重试次数
        props.put("retries", 0);
        //每个分区未发送消息总字节大小（单位：字节），超过设置的值就会提交数据到服务端
        props.put("batch.size", 10);
        //props.put("max.request.size",10);
        //消息在缓冲区保留的时间，超过设置的值就会被提交到服务端
        props.put("linger.ms", 10000);
        //整个Producer用到总内存的大小，如果缓冲区满了会提交数据到服务端
        //buffer.memory要大于batch.size，否则会报申请内存不足的错误
        props.put("buffer.memory", 10240);
        //序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<String, String>("test", 1, System.currentTimeMillis(),
                "123456789", "987654321"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println(JSONObject.toJSONString(recordMetadata));
                if (!Objects.isNull(e)){
                    System.out.println(ExceptionUtils.getStackTrace(e));
                }
            }
        });
        return ConfigurationLoader.getString("huya", "sss");
    }
}
