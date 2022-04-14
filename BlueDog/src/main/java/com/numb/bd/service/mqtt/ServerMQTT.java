package com.numb.bd.service.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
/**
 * Title:Server 这是发送消息的服务端
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author Unclue_liu
 */
public class ServerMQTT {

    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://115.29.227.115:41717";
    //定义一个主题
    public static final String TOPIC = "trajectory";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
    private static MqttTopic topic11;
    private String userName = "admin";  //非必须
    private String passWord = "password";  //非必须

    private static MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic11 = client.getTopic(TOPIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public static void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);

        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }


    public static void sendMessage(String clieId,String msg)throws Exception{
        ServerMQTT server = new ServerMQTT();
        server.message = new MqttMessage();
        server.message.setQos(1);  //保证消息能到达一次
        server.message.setRetained(true);
        String str ="{\"activetime\":\"20220229225420\",\"instid\":\"10000002\",\"latitude\":\"34.34720128640722\",\"longitude\":\"107.26220065170432\",\"mac\":\"2F3FEC0CED2028A5A4039C1C6639E58BB768DE325D28F93E180510A9137D39061E92603AC04391652389B46CA16AE98383667CA03FF52C586B9623ADB9B2953CA42E8B2244A131D8831DE9565D6A78DD43DAD891C70AA79DB438454002D807E2383A726BC62FEAE522515F36786FD7CD9A85CF5AB3116A2CC2E4CBA421D85F21\",\"mchntid\":\"1001937\",\"pushtime\":\"20220228225420\",\"termid\":\"17\",\"txntype\":\"trajectory\",\"userid\":\"9\"}";
        server.message.setPayload(str.getBytes());
        try{
            publish(server.topic11 , server.message);
            //断开连接
//            server.client.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws Exception {
        sendMessage("123444","哈哈");
    }
}
