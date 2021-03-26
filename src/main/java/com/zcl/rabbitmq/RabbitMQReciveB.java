package com.zcl.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * DESC :  队列B，处理发送短信
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value="${spring.rabbitmq.queueB}",autoDelete = "true"),
                exchange = @Exchange(value="${spring.rabbitmq.exchange}",type=ExchangeTypes.DIRECT),
                key="${spring.rabbitmq.routingkeyB}"
        )
)
public class RabbitMQReciveB {
    //接收电话号码，发送短息
    @RabbitHandler
    public void getMessage(String tel) throws InterruptedException {
        System.out.println("队列B接收电话号码："+ tel + "--------开始发送注册提示短信");
        Thread.sleep(3000);
        System.out.println("发送短信成功，耗时3秒");
    }

}