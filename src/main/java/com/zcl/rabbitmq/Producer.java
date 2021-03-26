package com.zcl.rabbitmq;

/**
 * .
 *
 * @Author Chenglin Zhu
 * @Date 2021/3/23 20:31
 */
public class Producer {

    static final String QUEUE_NAME = "simple_queue";

    /*public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明创建队列
        *//**
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其他参数
         *//*
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //要发送的消息
        String msg = "我的rabbitmq测试！！";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("已生产消息" + msg);
        channel.close();
        connection.close();
    }*/
}
