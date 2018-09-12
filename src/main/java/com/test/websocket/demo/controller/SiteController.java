package com.test.websocket.demo.controller;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.Site;
import com.test.websocket.demo.service.CreateRecordService;
import com.test.websocket.demo.service.GetHeaderService;
import com.test.websocket.demo.service.GetTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private CreateRecordService createRecordService;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 5000)
    public void greeting() {
        template.convertAndSend("/topic/greetings", createRecordService.getAllSite());
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<Header> createRecord(Site siteInfo) {
        return createRecordService.createNewRecord(siteInfo);
    }

    @MessageMapping("/stop")
    @SendTo("/topic/greetings")
    public void stopMonitoring(Site siteInfo) {
        createRecordService.stopMonitoring(siteInfo);
    }
}

