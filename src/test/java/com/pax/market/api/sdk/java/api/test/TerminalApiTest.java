/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.test;


import com.pax.market.api.sdk.java.api.base.dto.DownloadTaskDTO;
import com.pax.market.api.sdk.java.api.base.dto.EmptyResponse;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalLogRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.*;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalGroupRequest;
import com.pax.market.api.sdk.java.api.terminalGroup.dto.TerminalSnGroupRequest;
import com.pax.market.api.sdk.java.api.util.EnhancedJsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalSearchOrderBy;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;

import java.util.*;


/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class TerminalApiTest {

	private static final Logger logger = LoggerFactory.getLogger(TerminalApiTest.class.getSimpleName());

	TerminalApi terminalApi;

	@Before
    public void init(){
		terminalApi = new  TerminalApi(TestConstants.API_BASE_URL, TestConstants.API_KEY, TestConstants.API_SECRET);
    }

    @Test
    public void testSearchTerminalList(){
		Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, TerminalSearchOrderBy.Name, TerminalStatus.Inactive, "14494956");
		logger.debug("Result of search terminal: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
    }

	@Test
	public void testSearchTerminalListInluceGeoFirmAppDetail(){
		Result<TerminalDTO> result = terminalApi.searchTerminal(1, 10, TerminalSearchOrderBy.Name, null, "BTG7KFTY", true, true, true);
		logger.debug("Result of search terminal: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}
    

    @Test
    public void testCreateTerminal() {
		TerminalCreateRequest createReq = new TerminalCreateRequest();
		createReq.setName("KFC-TML-03");
		createReq.setMerchantName("merchant test");
		createReq.setResellerName("reseller test");
		createReq.setLocation("USA");
		createReq.setSerialNo("TJ000002");
		createReq.setModelName("A920");


		Result<TerminalDTO> result = terminalApi.createTerminal(createReq);
		logger.debug("Result of create terminal: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

		Long terminalId = result.getData().getId();


		Result<TerminalDTO> getResult = terminalApi.getTerminal(terminalId);
		logger.debug("Result of get terminal: {}",getResult.toString());
		Assert.assertTrue(getResult.getBusinessCode() == 0);


		//test update
		TerminalUpdateRequest updateReq = new TerminalUpdateRequest();
		updateReq.setName("KFC-TML-JS");
		updateReq.setModelName("A920");
		updateReq.setLocation("CN");
		updateReq.setSerialNo("TJ000002");
		updateReq.setResellerName("reseller test");
		updateReq.setMerchantName("merchant test");

		Result<TerminalDTO> updateResult = terminalApi.updateTerminal(terminalId, updateReq);
		logger.debug("Result of update terminal: {}",updateResult.toString());
		Assert.assertTrue(updateResult.getBusinessCode() == 0);

		//Test activate
		Result<String> activateResult = terminalApi.activateTerminal(terminalId);
		logger.debug("Result of activate terminal: {}",activateResult.toString());
		Assert.assertTrue(activateResult.getBusinessCode() == 0);


		//TEST copy
		TerminalCopyRequest copyRequest = new TerminalCopyRequest();
		copyRequest.setTerminalId(terminalId);
		copyRequest.setName("COPY_FROM_909822");
		copyRequest.setSerialNo("TJ00001002");
		copyRequest.setStatus(TerminalStatus.Inactive);
		Result<TerminalDTO> copyResult = terminalApi.copyTerminal(copyRequest);
		logger.debug("Result of copy terminal: {}",copyResult.toString());
		Assert.assertTrue(copyResult.getBusinessCode() == 0);

		//Test disable
		Result<String> disableResult = terminalApi.disableTerminal(terminalId);
		logger.debug("Result of disable terminal: {}",disableResult.toString());
		Assert.assertTrue(disableResult.getBusinessCode() == 0);

		//Test delete
		Result<String> deleteResult = terminalApi.deleteTerminal(terminalId);
		logger.debug("Result of delete terminal: {}",deleteResult.toString());
		Assert.assertTrue(deleteResult.getBusinessCode() == 0);

		Result<String> deleteCopyResult = terminalApi.deleteTerminal(copyResult.getData().getId());
		logger.debug("Result of delete copy terminal: {}",deleteCopyResult.toString());
		Assert.assertTrue(deleteCopyResult.getBusinessCode() == 0);


    }

    @Test
    public void testGetTerminalIncludeAccessoryInfo() {
		Long terminalId = 908627L;
		Result<TerminalDTO> getResult = terminalApi.getTerminal(terminalId,true);
		logger.debug("Result of get terminal: {}",getResult.toString());
		Assert.assertTrue(getResult.getBusinessCode() == 0);
    }

    @Test
    public void testGetTerminalIncludeInstalledApks() {
		Long terminalId = 909822L;
		Result<TerminalDTO> getResult = terminalApi.getTerminal(terminalId,false, true);
		logger.debug("Result of get terminal: {}",getResult.toString());
		Assert.assertTrue(getResult.getBusinessCode() == 0);
    }

	@Test
	public void testBatchAddTerminalToGroup(){
		TerminalGroupRequest groupRequest = new TerminalGroupRequest();
		Set<Long> terminalIds = new HashSet<>();
		terminalIds.add(909744L);
		terminalIds.add(909742L);
		Set<Long> groupIds = new HashSet<>();
		groupIds.add(16529L);
		groupIds.add(16527L);
		groupRequest.setTerminalIds(terminalIds);
		groupRequest.setGroupIds(groupIds);
		Result<String> result = terminalApi.batchAddTerminalToGroup(groupRequest);
		logger.debug("Result of search terminal: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalReplacementConfig(){
		Long terminalId = 909744L;
		TerminalReplacementUpdateRequest terminalReplacementUpdateRequest = new TerminalReplacementUpdateRequest();
		terminalReplacementUpdateRequest.setAllowReplacement(true);
		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalReplacementUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalTimeZoneConfig(){
		Long terminalId = 1547947831459887L;
		TerminalTimeZoneUpdateRequest terminalTimeZoneUpdateRequest = new TerminalTimeZoneUpdateRequest();
		terminalTimeZoneUpdateRequest.setAutomaticTimeZoneEnable(false);
		terminalTimeZoneUpdateRequest.setTimeZone(TimeZone.getDefault().getID());
		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalTimeZoneUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalApnConfig(){
		Long terminalId = 1639497804546092L;
		TerminalApnUpdateRequest terminalApnUpdateRequest = new TerminalApnUpdateRequest();
		TerminalApnUpdateRequest.ApnConfig apnConfig1 = EnhancedJsonUtils.fromJson("{\"name\":\"MyApn20\",\"mcc\":\"101\",\"mnc\":\"102\",\"apn\":\"APN01\",\"user\":\"\",\"proxy\":\"\",\"mmsport\":\"100\",\"mmsc\":\"\",\"authtype\":1,\"type\":\"net\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\"}", TerminalApnUpdateRequest.ApnConfig.class);
		TerminalApnUpdateRequest.ApnConfig apnConfig2 = EnhancedJsonUtils.fromJson("{\"name\":\"MyApn30\",\"mcc\":\"101\",\"mnc\":\"102\",\"apn\":\"APN01\",\"user\":\"\",\"proxy\":\"\",\"mmsport\":\"100\",\"mmsc\":\"\",\"authtype\":1,\"type\":\"net\",\"protocol\":\"IP\",\"roaming_protocol\":\"IP\",\"mvno_type\":\"spn\",\"mvno_match_data\":\"\"}", TerminalApnUpdateRequest.ApnConfig.class);
		List<TerminalApnUpdateRequest.ApnConfig> apnConfigList = new ArrayList<>();
		apnConfigList.add(apnConfig1);
		apnConfigList.add(apnConfig2);
		terminalApnUpdateRequest.setApnList(apnConfigList);

		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalApnUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalWifiConfig(){
		Long terminalId = 1639497804546092L;
		TerminalWifiUpdateRequest terminalWifiUpdateRequest = new TerminalWifiUpdateRequest();
		TerminalWifiUpdateRequest.WifiConfig wifiConfig1 = EnhancedJsonUtils.fromJson("{\"ssid\":\"pax30\",\"password\":\"12345678\",\"cipherType\":2,\"hostName\": \"1.1.1.1\",\"proxyType\":1,\"checked\": false}", TerminalWifiUpdateRequest.WifiConfig.class);
		TerminalWifiUpdateRequest.WifiConfig wifiConfig2 = EnhancedJsonUtils.fromJson("{\"ssid\":\"pax40\",\"password\":\"12345678\",\"cipherType\":2,\"hostName\": \"1.1.1.1\",\"proxyType\":1,\"checked\": false}", TerminalWifiUpdateRequest.WifiConfig.class);
		List<TerminalWifiUpdateRequest.WifiConfig> wifiConfigList = new ArrayList<>();
		wifiConfigList.add(wifiConfig1);
		wifiConfigList.add(wifiConfig2);
		terminalWifiUpdateRequest.setWifiList(wifiConfigList);
		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalWifiUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalLangConfig(){
		Long terminalId = 1639497804546092L;
		TerminalLanguageUpdateRequest terminalLanguageUpdateRequest = new TerminalLanguageUpdateRequest();
		terminalLanguageUpdateRequest.setLanguage("ja-jp");
		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalLanguageUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testUpdateTerminalWifiBlackListConfig(){
		Long terminalId = 1639497804546092L;
		TerminalWifiBlackListUpdateRequest terminalWifiBlackListUpdateRequest = new TerminalWifiBlackListUpdateRequest();
		TerminalWifiBlackListUpdateRequest.BlackListConfig blackListConfig1 = EnhancedJsonUtils.fromJson("{\"wifiName\":\"testwifi1\"}", TerminalWifiBlackListUpdateRequest.BlackListConfig.class);
		TerminalWifiBlackListUpdateRequest.BlackListConfig blackListConfig2 = EnhancedJsonUtils.fromJson("{\"wifiName\":\"testwifi2\"}", TerminalWifiBlackListUpdateRequest.BlackListConfig.class);
		List<TerminalWifiBlackListUpdateRequest.BlackListConfig> blackListConfigList = new ArrayList<>();
		blackListConfigList.add(blackListConfig1);
		blackListConfigList.add(blackListConfig2);
		terminalWifiBlackListUpdateRequest.setBlackList(blackListConfigList);

		Result<String> result = terminalApi.updateTerminalConfig(terminalId, terminalWifiBlackListUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);

	}

	@Test
	public void testGetTerminalConfig(){
		Long terminalId = 1639497804546092L;
		Result<TerminalConfigDTO> result = terminalApi.getTerminalConfig(terminalId);
		logger.debug("Result of get Terminal Config: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}


    @Test
	public void testGetTerminalPed(){
		Long terminalId = 909755L;
		Result<TerminalPedDTO> result = terminalApi.getTerminalPed(terminalId);
		logger.debug("Result of get Terminal ped: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}

	@Test
	public void testMoveTerminal() {
		Long terminalId = 1018664801L;
		Result<String> result = terminalApi.moveTerminal(terminalId, "PAX", "6666");
		logger.debug("Result of move Terminal {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}

	@Test
	public void testPushTerminalAction() {
		Long terminalId = 515006L;
		Result<String> result = terminalApi.pushCmdToTerminal(terminalId, TerminalApi.TerminalPushCmd.Unlock);
		logger.debug("Result of push terminal action {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testGetTerminalNetwork(){
		Result<TerminalNetworkDTO> result = terminalApi.getTerminalNetwork("TEST8000999","BTG7KFTY");
		logger.debug("Result of get Terminal network: {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}

	private final String serialNo = "TESTBYSN";
	private final Long terminalId = 1620067374596136L;
	private final String factoryName = "PAX";
	private final String modelName = "E500";

	@Test
	public void testTerminalBySn() {
		testUpdateTerminalBySn();
		testActivateTerminalBySn();
		testGetTerminalBySn();
		testCopyTerminalBySn();

		testSendTerminalMessage();
		testBatchAddTerminalToGroupBySn();
		testUpdateTerminalConfigBySn();
		testMoveTerminalBySn();
		testPushTerminalActionBySn();

		testGetTerminalIncludeAccessoryInfoBySn();
		testGetTerminalIncludeInstalledApksBySn();
		testGetTerminalConfigBySn();
		testGetTerminalPedBySn();

		testDisableTerminalBySn();
		testDeleteTerminalBySn();
	}
	@Test
	public void testGetTerminalIncludeAccessoryInfoBySn() {
		Result<TerminalDTO> getResult = terminalApi.getTerminalBySn(serialNo,true);
		logger.debug("Result of get terminal: {}",getResult.toString());
        Assert.assertEquals(0, getResult.getBusinessCode());
	}

	@Test
	public void testGetTerminalIncludeInstalledApksBySn() {
		Result<TerminalDTO> getResult = terminalApi.getTerminalBySn(serialNo,false, true);
		logger.debug("Result of get terminal: {}",getResult.toString());
        Assert.assertEquals(0, getResult.getBusinessCode());
	}

	@Test
	public void testBatchAddTerminalToGroupBySn(){
		TerminalSnGroupRequest groupRequest = new TerminalSnGroupRequest();
		Set<String> serialNoList = new HashSet<>();
		serialNoList.add(serialNo);
//		serialNoList.add("QWJQW028");
		Set<Long> groupIds = new HashSet<>();
		groupIds.add(16626L);
		groupRequest.setSerialNos(serialNoList);
		groupRequest.setGroupIds(groupIds);
		Result<String> result = terminalApi.batchAddTerminalToGroupBySn(groupRequest);
		logger.debug("Result of search terminal: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());

	}

	@Test
	public void testUpdateTerminalConfigBySn(){
		TerminalReplacementUpdateRequest terminalConfigUpdateRequest = new TerminalReplacementUpdateRequest();
		terminalConfigUpdateRequest.setAllowReplacement(true);
		Result<String> result = terminalApi.updateTerminalConfigBySn(serialNo,terminalConfigUpdateRequest);
		logger.debug("Result of update Terminal Config: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());

	}

	@Test
	public void testGetTerminalConfigBySn(){
		Result<TerminalConfigDTO> result = terminalApi.getTerminalConfigBySn(serialNo);
		logger.debug("Result of get Terminal Config: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());

	}


	@Test
	public void testGetTerminalPedBySn(){
		Result<TerminalPedDTO> result = terminalApi.getTerminalPedBySn(serialNo);
		logger.debug("Result of get Terminal ped: {}",result.toString());
        Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testMoveTerminalBySn() {
		Result<String> result = terminalApi.moveTerminalBySn(serialNo, "shifan", "merchant0");
		logger.debug("Result of move Terminal {}",result.toString());
		Assert.assertTrue(result.getBusinessCode() == 0);
	}

	@Test
	public void testPushTerminalActionBySn() {
		Result<String> result = terminalApi.pushCmdToTerminalBySn(serialNo, TerminalApi.TerminalPushCmd.Lock);
		logger.debug("Result of push terminal action {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testActivateTerminalBySn() {
		Result<String> result = terminalApi.activateTerminalBySn(serialNo);
		logger.debug("Result of activateTerminalBySn {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testDeleteTerminalBySn() {
		Result<String> result = terminalApi.deleteTerminalBySn(serialNo);
		logger.debug("Result of deleteTerminalBySn {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testCopyTerminalBySn() {
		TerminalSnCopyRequest request=new TerminalSnCopyRequest();
		request.setSourceSerialNo(serialNo);
		request.setSerialNo("TESTCOPYBYSN");
		request.setStatus(TerminalStatus.Inactive);
		request.setName("MyTerminal240524");
		Result<TerminalDTO> result = terminalApi.copyTerminalBySn(request);
		logger.debug("Result of testCopyTerminalBySn {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testUpdateTerminalBySn() {
		TerminalUpdateRequest updateRequest = new TerminalUpdateRequest();
		updateRequest.setSerialNo(serialNo);
		updateRequest.setName("Terminal No.7637");
		updateRequest.setResellerName("shifan");
		updateRequest.setRemark("new Remark2024");
		updateRequest.setModelName("A920");
		updateRequest.setMerchantName("merchant to update");

		Result<TerminalDTO> terminalDTOResult = terminalApi.updateTerminalBySn(serialNo, updateRequest);
		logger.debug("Result of testCopyTerminalBySn {}", terminalDTOResult.toString());
		Assert.assertEquals(0, terminalDTOResult.getBusinessCode());
	}

	@Test
	public void testDisableTerminalBySn() {
		Result<String> result = terminalApi.disableTerminalBySn(serialNo);
		logger.debug("Result of testDisableTerminalBySn {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testGetTerminalBySn() {
		Result<TerminalDTO> terminalBySn = terminalApi.getTerminalBySn(serialNo);
		logger.debug("Result of testGetTerminalBySn {}", terminalBySn.toString());
		Assert.assertEquals(0, terminalBySn.getBusinessCode());
	}

	@Test
	public void testSendTerminalMessage() {
		//terminal is online
		TerminalMessageRequest request = new TerminalMessageRequest();
		request.setTitle("Test Title");
		request.setContent("Test Content");
		Result<String> result = terminalApi.pushTerminalMessage(terminalId,request);
		logger.debug("Result of get result: {}",result.toString());
		Assert.assertEquals(0, result.getBusinessCode());

		Result<String> resultBySn = terminalApi.pushTerminalMessageBySn(serialNo,request);
		logger.debug("Result of get result: {}",resultBySn.toString());
		Assert.assertEquals(0, resultBySn.getBusinessCode());
	}

	@Test
	public void testTerminalSystemUsageBySN() {
		Result<TerminalSystemUsageDTO> terminalSystemUsage = terminalApi.getTerminalSystemUsageBySn("SUBSN108");
		if(terminalSystemUsage.getData()!=null) {
			logger.debug("Result of testTerminalCpuStatistic {}",terminalSystemUsage.getData().toString());
		}else{
			logger.debug("Result of testTerminalCpuStatistic {}", terminalSystemUsage.toString());
		}
		Assert.assertEquals(0, terminalSystemUsage.getBusinessCode());
	}

	@Test
	public void testTerminalSystemUsageById() {
		Result<TerminalSystemUsageDTO> terminalSystemUsage = terminalApi.getTerminalSystemUsageById(1639491781525547L);
		if(terminalSystemUsage.getData()!=null) {
			logger.debug("Result of testTerminalCpuStatistic {}",terminalSystemUsage.getData().toString());
		}else{
			logger.debug("Result of testTerminalCpuStatistic {}", terminalSystemUsage.toString());
		}
		Assert.assertEquals(0, terminalSystemUsage.getBusinessCode());
	}

	@Test
	public void testCollectTerminalLog() {
		TerminalLogRequest request = new TerminalLogRequest();
		request.setType("L");
		Result<String> result = terminalApi.collectTerminalLog(terminalId,request);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testCollectTerminalLogBySn() {
		TerminalLogRequest request = new TerminalLogRequest();
		request.setType("L");
		Result<String> result = terminalApi.collectTerminalLogBySn(serialNo,request);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testSearchTerminalLog() {
		Result<TerminalLogDTO> result = terminalApi.searchTerminalLog(1, 10, terminalId);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testSearchTerminalLogBySn() {
		Result<TerminalLogDTO> result = terminalApi.searchTerminalLogBySn(1, 10, serialNo);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testGetTerminalLogDownloadTask() {
		Long terminalLogId = 1018664888L;
		Result<DownloadTaskDTO> result = terminalApi.getTerminalLogDownloadTask(terminalId, terminalLogId);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testGetTerminalLogDownloadTaskBySn() {
		Long terminalLogId = 1018664888L;
		Result<DownloadTaskDTO> result = terminalApi.getTerminalLogDownloadTaskBySn(serialNo, terminalLogId);
		Assert.assertEquals(0, result.getBusinessCode());
	}

	@Test
	public void testChangeModelBySN() {
		Result<EmptyResponse> responseResult = terminalApi.changeModelBySN("SUBSN108", factoryName, modelName);
		Assert.assertEquals(0, responseResult.getBusinessCode());
	}

	@Test
	public void testChangeModelById() {
		Result<EmptyResponse> responseResult = terminalApi.changeModel(1639491781525547L, factoryName, modelName);
		Assert.assertEquals(0, responseResult.getBusinessCode());
	}
}
