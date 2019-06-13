package com.lhm.jpa13.kafka.provider;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lhm.jpa13.kafka.Message;
import com.lhm.jpa13.kafka.common.constant.TopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaSender {
    private static Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void send(Message payMessage) {
        String msg = gson.toJson(payMessage);
        kafkaTemplate.send(TopicConst.PAY_TOPIC, msg);
        logger.info("MessageProducer: send: message is: [" + msg + "]");

    }

}
