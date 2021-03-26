package com.zcl.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * .
 *
 * @Author Chenglin Zhu
 * @Date 2021/3/23 20:28
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //主机地址，默认为localhost
        factory.setHost("127.0.0.1");
        //连接端口(并不是web管理后台的15672)
        factory.setPort(5672);
        //设置虚拟主机
        factory.setVirtualHost("/mytest");
        //连接用户名，默认为guest
        factory.setUsername("guest");
        //密码，默认为guest
        factory.setPassword("guest");
        //创建连接
        return factory.newConnection();
    }
}
