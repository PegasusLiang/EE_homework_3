package com.lhm.jpa13.kafka.consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lhm.jpa13.kafka.Message;
import com.lhm.jpa13.kafka.common.constant.TopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = TopicConst.PAY_TOPIC)
    public void onMessage(String payMessage) {
        Message msg = gson.fromJson(payMessage, Message.class);
        logger.info("MessageConsumer: onMessage: message is: [" + msg + "]");
    }

}
