package com.test.websocket.demo.service;

import com.test.websocket.demo.model.Header;
import com.test.websocket.demo.model.SiteResponse;

public interface GetHeaderService {

    SiteResponse getSiteResponse(Header header);
}
