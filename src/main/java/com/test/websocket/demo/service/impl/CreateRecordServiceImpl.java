package com.test.websocket.demo.service.impl;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.Site;
import com.test.websocket.demo.model.SiteResponse;
import com.test.websocket.demo.service.CreateRecordService;
import com.test.websocket.demo.service.GetHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateRecordServiceImpl implements CreateRecordService {

    @Autowired
    private GetHeaderService getHeaderService;

    private List<Header> siteList = new ArrayList<>();

    @Override
    public List<Header> createNewRecord(Site siteInfo) {

        boolean checkList = true;

        outer: for (Header elem : siteList) {
            SiteResponse siteResponse = getHeaderService.getSiteResponse(elem);
            if (elem.getSiteName().equals(siteInfo.getName()) && !elem.isMonitor()) {
                continue outer;
            } else if (elem.getSiteName().equals(siteInfo.getName())) {
                elem.setServerStatus(siteResponse.getHeadStatus());
                elem.setTime((int) siteResponse.getResponseTime());
                elem.setLengthServerStatus(siteInfo.getName().concat(siteInfo.getWord()).length());
                elem.setWord(siteResponse.isCheckWord());
                elem.setSiteName(siteInfo.getName());
                checkList = false;
            }
        }

        if (checkList) {
            Header tempHeader = new Header();
            tempHeader.setSiteName(siteInfo.getName());
            tempHeader.setRequestWord(siteInfo.getWord());
            SiteResponse siteResponse = getHeaderService.getSiteResponse(tempHeader);
            Header header = new Header(siteResponse.getHeadStatus()
                    , (int) siteResponse.getResponseTime()
                    , siteInfo.getName().concat(siteInfo.getWord()).length()
                    , siteResponse.isCheckWord()
                    , siteInfo.getName()
                    , true
                    , siteInfo.getWord());

            siteList.add(header);
        }

        return siteList;
    }

    @Override
    public List<Header> getAllSite() {
        outer: for (Header elem : siteList) {
            SiteResponse siteResponse = getHeaderService.getSiteResponse(elem);
            if (!elem.isMonitor()) {
                continue outer;
            } else {
                elem.setServerStatus(siteResponse.getHeadStatus());
                elem.setTime((int) siteResponse.getResponseTime());
                elem.setSiteName(elem.getSiteName());
                elem.setWord(siteResponse.isCheckWord());
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
