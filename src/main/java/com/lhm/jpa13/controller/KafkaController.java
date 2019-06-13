package com.lhm.jpa13.controller;

import com.lhm.jpa13.kafka.Message;
import com.lhm.jpa13.kafka.common.util.ToolsUtil;
import com.lhm.jpa13.kafka.provider.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KafkaController {
        @Autowired
        private KafkaSender producer;
        @RequestMapping("/kafka")
        @ResponseBody
        public String access(){
            while(true){
                Message message = new Message();
                message.setFee(ToolsUtil.getFee());
                message.setOrderCode(ToolsUtil.getNextCode());
                message.setSendTime(System.currentTimeMillis());
                producer.send(message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return  message.toString();
            }

        }


}
