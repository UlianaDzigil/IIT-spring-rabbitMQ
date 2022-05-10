package com.dailycodebuffer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;
@Component
public class MessageListener {
    @Autowired
    ConsumerBot bot;

    /*@Value("0")
    Integer time;*/

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
        System.out.println(message.getClass());
        bot.writeMessage(message);
        /*Timer timer = new Timer("Timer");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bot.writeMessage(message);
                time = time + 30000;
            }
        }, time);
        time = 0;*/
    }

}
