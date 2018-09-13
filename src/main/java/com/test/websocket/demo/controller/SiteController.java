package com.test.websocket.demo.controller;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.Site;
import com.test.websocket.demo.service.CreateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private CreateRecordService createRecordService;

    @Autowired
    private SimpMessageSendingOperations template;

    @Scheduled(fixedDelay = 5000)
    public void greeting() {
        template.convertAndSend("/topic/greetings", createRecordService.getAllSite());
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<Header> createRecord(@Payload Site siteInfo) {
        return createRecordService.createNewRecord(siteInfo);
    }

    @MessageMapping("/stop")
    @SendTo("/topic/greetings")
    public void stopMonitoring(@Payload Site siteInfo) {
        createRecordService.stopMonitoring(siteInfo);
    }
}

