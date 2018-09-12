package com.test.websocket.demo.service.impl;

import com.test.websocket.demo.service.GetTimeService;
import org.springframework.stereotype.Service;
import com.test.websocket.demo.util.GetConnection;

import java.net.HttpURLConnection;

@Service
public class GetTimeServiceImpl implements GetTimeService {

    @Override
    public long responseTime(String site) {

        long start = System.currentTimeMillis();

        GetConnection getConnection = new GetConnection();
        HttpURLConnection connect = getConnection.getConnect(site);
        getConnection.getConnect(site);

        long end = System.currentTimeMillis();

        if (connect == null) {
            return 0;
        }

        return end - start;
    }
}
