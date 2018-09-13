package com.test.websocket.demo.service.impl;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.SiteResponse;
import com.test.websocket.demo.service.GetHeaderService;
import org.springframework.stereotype.Service;
import com.test.websocket.demo.util.GetConnection;

import java.net.HttpURLConnection;

@Service
public class GetHeaderServiceImpl implements GetHeaderService {

    @Override
    public SiteResponse getSiteResponse(Header header) {

        SiteResponse siteResponse = new SiteResponse();

        long start = System.currentTimeMillis();
        GetConnection getConnection = new GetConnection();
        HttpURLConnection connect = getConnection.getConnect(header.getSiteName());
        long end = System.currentTimeMillis();
        String headerField = connect.getHeaderField(0);

        siteResponse.setResponseTime(end - start);
        siteResponse.setHeadStatus(headerField);

        String toLowerCaseHeader = headerField.toLowerCase();
        String toLowerCaseWord = header.getRequestWord().toLowerCase();

        siteResponse.setCheckWord(toLowerCaseHeader.contains(toLowerCaseWord));

        return siteResponse;
    }
}
