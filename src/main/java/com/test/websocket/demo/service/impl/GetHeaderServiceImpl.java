package com.test.websocket.demo.service.impl;

import com.test.websocket.demo.service.GetHeaderService;
import org.springframework.stereotype.Service;
import com.test.websocket.demo.util.GetConnection;

import java.net.HttpURLConnection;

@Service
public class GetHeaderServiceImpl implements GetHeaderService {

    @Override
    public String getHeader(String site) {

        GetConnection getConnection = new GetConnection();
        HttpURLConnection connect = getConnection.getConnect(site);

        return connect.getHeaderField(0);
    }

    @Override
    public boolean checkWord(String word, String site) {

        GetConnection getConnection = new GetConnection();
        HttpURLConnection connect = getConnection.getConnect(site);
        String headerField = connect.getHeaderField(0);
        String toLowerCaseHeader = headerField.toLowerCase();
        String toLowerCaseWord = word.toLowerCase();
        return toLowerCaseHeader.contains(toLowerCaseWord);
    }
}
