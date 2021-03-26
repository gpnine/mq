package com.zcl.rabbitmq;

/**
 * .
 *
 * @Author Chenglin Zhu
 * @Date 2021/3/23 20:37
 */
public class Consumer {

    /*public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Producer.QUEUE_NAME, true, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由key为：" + envelope.getRoutingKey());
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                System.out.println("接收到的消息：" + new String(body, "Utf-8"));
                System.out.println("==============================");
            }
        };
        *//**
         * 监听消息
         *  参数一：队列名称
         *  参数二：是否自动确认（true表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认）
         *
         *//*
        channel.basicConsume(Producer.QUEUE_NAME, true, consumer);
        //不关闭资源，应该保持监听
        *//*channel.close();
        connection.close();*//*
    }*/

}
