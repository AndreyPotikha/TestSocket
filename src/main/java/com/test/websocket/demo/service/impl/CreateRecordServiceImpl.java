package com.test.websocket.demo.service.impl;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.Site;
import com.test.websocket.demo.service.CreateRecordService;
import com.test.websocket.demo.service.GetHeaderService;
import com.test.websocket.demo.service.GetTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateRecordServiceImpl implements CreateRecordService {

    @Autowired
    private GetHeaderService getHeaderService;

    @Autowired
    private GetTimeService getTimeService;

    private List<Header> siteList = new ArrayList<>();

    @Override
    public List<Header> createNewRecord(Site siteInfo) {

        boolean checkLict = true;

        outer: for (Header elem : siteList) {
            if (elem.getSiteName().equals(siteInfo.getName()) && !elem.isMonitor()) {
                continue outer;
            } else if (elem.getSiteName().equals(siteInfo.getName())) {
                elem.setServerStatus(getHeaderService.getHeader(siteInfo.getName()));
                elem.setTime((int) getTimeService.responseTime(siteInfo.getName()));
                elem.setLengthServerStatus(siteInfo.getName().concat(siteInfo.getWord()).length());
                elem.setWord(getHeaderService.checkWord(siteInfo.getWord(), siteInfo.getName()));
                elem.setSiteName(siteInfo.getName());
                checkLict = false;
            }
        }

        if (checkLict) {
            Header header = new Header(getHeaderService.getHeader(siteInfo.getName())
                    , (int) getTimeService.responseTime(siteInfo.getName())
                    , siteInfo.getName().concat(siteInfo.getWord()).length()
                    , getHeaderService.checkWord(siteInfo.getWord(), siteInfo.getName())
                    , siteInfo.getName()
                    , true);

            siteList.add(header);
        }

        return siteList;
    }

    @Override
    public List<Header> getAllSite() {
        outer: for (Header elem : siteList) {
            if (!elem.isMonitor()) {
                continue outer;
            } else {
                elem.setServerStatus(getHeaderService.getHeader(elem.getSiteName()));
                elem.setTime((int) getTimeService.responseTime(elem.getSiteName()));
                elem.setSiteName(elem.getSiteName());
            }
        }
        return siteList;
    }

    @Override
    public void stopMonitoring(Site siteInfo) {
        for (Header elem : siteList) {
            if (elem.getSiteName().equals(siteInfo.getName())) {
                elem.setMonitor(false);
            }
        }
    }
}
