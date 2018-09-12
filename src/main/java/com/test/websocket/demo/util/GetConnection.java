package com.test.websocket.demo.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetConnection {

    public HttpURLConnection getConnect(String site) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(site).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            connection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return connection;
    }
}
