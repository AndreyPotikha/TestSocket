package com.test.websocket.demo.service;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.Site;

import java.util.List;

public interface CreateRecordService {

    List<Header> createNewRecord(Site siteInfo);

    List<Header> getAllSite();
}
