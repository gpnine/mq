package com.zcl.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * DESC :  队列A，处理发送邮件
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "${spring.rabbitmq.queueA}", autoDelete = "true"),
                exchange = @Exchange(value = "${spring.rabbitmq.exchange}", type = ExchangeTypes.DIRECT),
                key = "${spring.rabbitmq.routingkeyA}"
        )
)
public class RabbitMQReciveA {


    //接收邮箱账号，发送邮件
    @RabbitHandler
    public void getMessage(String email) throws InterruptedException {
        System.out.println("队列A接收邮箱账号：" + email + "--------开始发送注册提示邮件");
        Thread.sleep(3000);
        System.out.println("发送邮件成功，耗时3秒");
    }
}