package com.zcl.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * .
 *
 * @Author Chenglin Zhu
 * @Date 2021/3/24 11:20
 */
@Controller
public class RabbitMQController {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.exchange}")
    String exchangeName;    //交换器名称

    @Value("${spring.rabbitmq.routingkeyA}")
    String routeA;

    @Value("${spring.rabbitmq.routingkeyB}")
    String routeB;


    //一般的注册流程  同步进行
    @RequestMapping(value = "/generalReg", method = RequestMethod.GET)
    @ResponseBody
    public String generalReg(@RequestParam String email, @RequestParam String tel) throws InterruptedException {
        //入库
        System.out.println("入库操作");

        //发送邮件
        System.out.println("发送提示邮件给用户");
        Thread.sleep(3000);
        System.out.println("发送提示邮件耗时3秒");

        //发送短信
        System.out.println("发送提示短信给用户");
        Thread.sleep(3000);
        System.out.println("发送提示短信耗时3秒");

        //返回注册结果
        return "注册成功";
    }


    //加入RabbitMQ后的注册流程  异步进行
    @RequestMapping(value = "/rabbitmqReg", method = RequestMethod.GET)
    @ResponseBody
    public String sendA(@RequestParam String email, @RequestParam String tel) throws InterruptedException {

        //入库
        System.out.println("入库操作");

        //发送邮件  只需要把邮箱发给消息队列A，让它去异步去发邮件
        System.out.println("发送提示邮件给用户");
        amqpTemplate.convertAndSend(exchangeName, routeA, email);

        //发送短信  只需要把电话号码发给消息队列B，让它去异步去发短信
        try {
            int a = 1 / 0;
            System.out.println("发送提示短信给用户");
            amqpTemplate.convertAndSend(exchangeName, routeB, tel);
        } catch (Exception e) {
            System.out.println("异常"+e.getMessage());
        }


        //返回注册结果
        return "注册成功";
    }

    @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
    @ResponseBody
    public String sendSms(@RequestParam String phoneNumber) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new AsyncSendSms(phoneNumber));
        service.shutdown();
        return "我做完了";
    }

    static class AsyncSendSms implements Runnable {
        private final String phoneNumber;

        public AsyncSendSms(String phoneNumber) {
            super();
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void run() {
            try {
                System.out.println("我要发短信给：" + phoneNumber);
                Thread.sleep(5000L);
                System.out.println("已发送");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
