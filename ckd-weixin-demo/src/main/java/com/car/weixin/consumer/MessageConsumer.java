package com.car.weixin.consumer;


import com.car.weixin.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 功能概要：消费接收
 */
//public class MessageConsumer implements MessageListener {
public class MessageConsumer{

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

//    @Override
    public void onMessage(Message message) {
        logger.info("receive message:{}",message);

//        try{
//            Thread.sleep(3000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        System.out.println("++++++++++++++++++++++++++++++message.toString(): " + new String(message.getBody()));
    }


    public void handleMessage(User user) {
        System.out.println("Received: " + user);
        System.out.println("openId="+user.getOpenId());
        System.out.println(user.toString());
    }
}