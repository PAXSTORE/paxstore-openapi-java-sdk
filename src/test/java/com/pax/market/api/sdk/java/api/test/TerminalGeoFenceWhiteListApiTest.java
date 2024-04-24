package com.pax.market.api.sdk.java.api.test;

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.TerminalGeoFenceWhiteListApi;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListDTO;
import com.pax.market.api.sdk.java.api.terminalGeoFenceWhiteList.dto.TerminalGeoFenceWhiteListRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class TerminalGeoFenceWhiteListApiTest {
    private static final Logger logger = LoggerFactory.getLogger(TerminalGeoFenceWhiteListApiTest.class.getSimpleName());

    static TerminalGeoFenceWhiteListApi terminalGeoFenceWhiteListApi;

    @Before
    public void init(){
        terminalGeoFenceWhiteListApi = new TerminalGeoFenceWhiteListApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchGeoFenceWhiteList(){
        String serialNo = "";
        Result<TerminalGeoFenceWhiteListDTO> result = terminalGeoFenceWhiteListApi.searchGeoFenceWhiteList(1,10, TerminalGeoFenceWhiteListApi.SearchOrderBy.CreatedDate_desc, serialNo);
        logger.debug("Result of  search geofence whitelist: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }

    @Test
    public void testCreateGeoFenceWhiteList(){
        TerminalGeoFenceWhiteListRequest createRequest = new TerminalGeoFenceWhiteListRequest();
        createRequest.setSerialNo("1123321123sn");
        Result<String> result = terminalGeoFenceWhiteListApi.createGeoFenceWhiteList(createRequest);
        logger.debug("Result of  create geofence whitelist: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);
    }


    @Test
    public void  testDeleteGeoFenceWhiteList() throws IOException {
        TerminalGeoFenceWhiteListRequest deleteRequest = new TerminalGeoFenceWhiteListRequest();
        deleteRequest.setSerialNo("1123321123sn");
        Result<String> result = terminalGeoFenceWhiteListApi.deleteGeoFenceWhiteList(deleteRequest);
        logger.debug("Result of delete geofence whitelist: {}",result.toString());
        Assert.assertTrue(result.getBusinessCode() == 0);

    }


}
