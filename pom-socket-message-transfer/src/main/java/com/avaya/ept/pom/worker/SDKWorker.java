package com.avaya.ept.pom.worker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avaya.ept.pom.pojo.MessageObj;
import com.avaya.sdk.PAMSocketInfo;
import com.avaya.sdk.Agent.POMAgent;
import com.avaya.sdk.Agent.POMAgentHandlerInterface;
import com.avaya.sdk.Data.POMAgentState;
import com.avaya.sdk.Data.POMAgentStatus;
import com.avaya.sdk.Data.POMAttribute;
import com.avaya.sdk.Data.POMCallState;
import com.avaya.sdk.Data.POMCallbackDest;
import com.avaya.sdk.Data.POMCallbackType;
import com.avaya.sdk.Data.POMCapabilities;
import com.avaya.sdk.Data.POMCompletionCode;
import com.avaya.sdk.Data.POMContact;
import com.avaya.sdk.Data.POMContactNumber;
import com.avaya.sdk.Data.POMContextStoreData;
import com.avaya.sdk.Data.POMCustomerDetails;
import com.avaya.sdk.Data.POMDestination;
import com.avaya.sdk.Data.POMDestinationType;
import com.avaya.sdk.Data.POMDialFailReason;
import com.avaya.sdk.Data.POMErrorInfo;
import com.avaya.sdk.Data.POMKeyValuePair;
import com.avaya.sdk.Data.POMNailupStatus;
import com.avaya.sdk.Data.POMWrapupDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SDKWorker implements POMAgentHandlerInterface {
    
    private static final Logger logger = LogManager.getLogger(SDKWorker.class);
    
    private Session socketSession;
    
    private String sessionID;
    
    private POMCompletionCode POMCompletionCode;
    
    private POMContactNumber POMContactNumber;
    
    private boolean isPreview = false;
    
    private POMAgent pomAgtObj;
    
    private POMAgentStatus agtStatus;
    
    private String agentId;
    
    private Date callStartTime = null;
    
    private Date wrapUpTime = null;
    
    public SDKWorker(String agentId) {
        this.agentId = agentId;
    }
    
    public void setPomAgtObj(POMAgent pomAgtObj) {
        this.pomAgtObj = pomAgtObj;
        
    }
    
    public POMAgent getPomAgtObj() {
        return this.pomAgtObj;
    }
    
    public Session getSocketSession() {
        return socketSession;
    }
    
    public void setSocketSession(Session socketSession) {
        this.socketSession = socketSession;
    }
    
    public Date getCallStartTime() {
        return callStartTime;
    }
    
    public Date getWrapUpTime() {
        return wrapUpTime;
    }
    
    public POMCompletionCode getPOMCompletionCode() {
        return POMCompletionCode;
    }
    
    @Override
    public void AGTSetCustomerDetailRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTSetCustomerDetailRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTSetCustomerDetailRESP");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTBlendToInboundRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTBlendToInboundRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTBlendToInboundRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTBlendToOutboundRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTBlendToOutboundRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTBlendToOutboundRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTNailupAgentRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTNailupAgentRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTNailupAgentRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTReadyForNailupRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTReadyForNailupRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTReadyForNailupRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTLostNailingRESP(POMWrapupDetails wrapupDetails, String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTLostNailingRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTLostNailingRESP");
        resp.put("sessionID", sessionID);
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTPendingLogoutRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTPendingLogoutRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTPendingLogoutRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void GetAgentStatusResponseRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::GetAgentStatusResponseRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "GetAgentStatusResponseRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTAddAgentNoteRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTAddAgentNoteRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTAddAgentNoteRESP");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTRefreshAgentNotesRESP(String[] agentNotes, String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTRefreshAgentNotesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTRefreshAgentNotesRESP");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTGetTimezonesRESP(POMKeyValuePair[] timezones, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetTimezonesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTGetTimezonesRESP");
        resp.put("timezones", timezones);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTAvailableForNailupRESP(int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTAvailableForNailupRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTAvailableForNailupRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTAgentDisconnectedRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTAgentDisconnectedRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTAgentDisconnectedRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTAddToDNCRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTAddToDNCRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTAddToDNCRESP");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AgentSDKConnectedRESP(HashMap<String, PAMSocketInfo> zoneIPMap) throws Exception {
        
        logger.info("[POM => AGENT] Method::AgentSDKConnectedRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AgentSDKConnectedRESP");
        resp.put("zoneIPMap", zoneIPMap);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void GetPAMForZoneRESP(HashMap<String, PAMSocketInfo> zoneIPMap) throws Exception {
        logger.info("[POM => AGENT] Method::GetPAMForZoneRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "GetPAMForZoneRESP");
        resp.put("zoneIPMap", zoneIPMap);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void GetAgentStatus(String agentID) throws Exception {
        
        logger.info("[POM => AGENT] Method::GetAgentStatus Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "GetAgentStatus");
        resp.put("agentID", agentID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTGetZoneListRESP(String[] zoneList, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetZoneListRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTGetZoneListRESP");
        resp.put("zoneList", zoneList);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTIsInDNCRESP(boolean present, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTIsInDNCRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTIsInDNCRESP");
        resp.put("present", present);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTSaveAgentForHARESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTSaveAgentForHARESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTSaveAgentForHARESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTSkillsChangedRESP(int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTSkillsChangedRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTSkillsChangedRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTGetContactAttributesRESP(POMAttribute[] pomContactAttributes, String sessionID, int result)
            throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTGetContactAttributesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTGetContactAttributesRESP");
        resp.put("sessionID", sessionID);
        resp.put("pomContactAttributes", pomContactAttributes);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTStateChangedNotify(POMAgentState agentState) throws Exception {
        logger.info("[POM => AGENT] Method::AGTStateChangedNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTStateChangedNotify");
        resp.put("agentState", agentState);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTCallNotify(POMContact contact, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCallNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTCallNotify");
        resp.put("sessionID", sessionID);
        resp.put("contact", contact);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTAutoReleaseLine(POMWrapupDetails wrapupDetails, String sessionID) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTAutoReleaseLine Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTAutoReleaseLine");
        resp.put("sessionID", sessionID);
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTConsultNotify(POMContact contact, String requestingAgentId, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConsultNotify");
        resp.put("sessionID", sessionID);
        resp.put("requestingAgentId", requestingAgentId);
        resp.put("contact", contact);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTConsultCancelled(String requestingAgentID, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultCancelled Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConsultCancelled");
        resp.put("sessionID", sessionID);
        resp.put("requestingAgentID", requestingAgentID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTTransferNotify(POMContact contact, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTTransferNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTTransferNotify");
        resp.put("sessionID", sessionID);
        resp.put("contact", contact);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTConferenceNotify(POMContact pomContact, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConferenceNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConferenceNotify");
        resp.put("sessionID", sessionID);
        resp.put("pomContact", pomContact);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTConferenceEnded(String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConferenceEnded Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConferenceEnded");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
        
    }
    
    @Override
    public void AGTConferenceOwnershipChanged(POMContact pomContact, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConferenceOwnershipChanged Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConferenceOwnershipChanged");
        resp.put("sessionID", sessionID);
        resp.put("pomContact", pomContact);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTCapabilitiesChanged(POMCapabilities capabilities) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCapabilitiesChanged Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTCapabilitiesChanged");
        resp.put("capabilities", capabilities);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTNailupChange(POMNailupStatus nailupStatus) throws Exception {
        logger.info("[POM => AGENT] Method::AGTNailupChange Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTNailupChange");
        resp.put("nailupStatus", nailupStatus);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTCallStateChangedNotify(POMCallState callState) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCallStateChangedNotify Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTCallStateChangedNotify");
        resp.put("callState", callState);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTDialFailed(POMWrapupDetails wrapupDetails, POMDialFailReason dialFailReason, String sessionID)
            throws Exception {
        logger.info("[POM => AGENT] Method::AGTDialFailed Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTDialFailed");
        resp.put("wrapupDetails", wrapupDetails);
        resp.put("sessionID", sessionID);
        resp.put("dialFailReason", dialFailReason);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTConsultDialFailed(POMDialFailReason dialFailReason, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultDialFailed Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConsultDialFailed");
        resp.put("dialFailReason", dialFailReason);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTConsultPending(String requestingAgent, String requestingAgentID, String requestingCampaign,
            String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultDialFailed Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConsultDialFailed");
        resp.put("requestingAgent", requestingAgent);
        resp.put("requestingAgentID", requestingAgentID);
        resp.put("requestingCampaign", requestingCampaign);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTPendingConsultComplete(String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTPendingConsultComplete Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTPendingConsultComplete");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void POMAvailable() throws Exception {
        
        logger.info("[POM => AGENT] Method::POMAvailable Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "POMAvailable");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void POMNotAvailable() throws Exception {
        
        logger.info("[POM => AGENT] Method::POMNotAvailable Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "POMNotAvailable");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTPreviewCallbackPending(String dueTime, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTPreviewCallbackPending Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTPreviewCallbackPending");
        resp.put("sessionID", sessionID);
        resp.put("dueTime", dueTime);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTPreviewCallbackCancelled(String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTPreviewCallbackCancelled Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTPreviewCallbackCancelled");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTEnableCancelConsult(String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTEnableCancelConsult Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTEnableCancelConsult");
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTInvalidCommandName(String commandName) throws Exception {
        logger.info("[POM => AGENT] Method::AGTInvalidCommandName Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTInvalidCommandName");
        resp.put("commandName", commandName);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTCustomerDetailsChanged(POMAttribute detail, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCustomerDetailsChanged Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTCustomerDetailsChanged");
        resp.put("detail", detail);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTBlendedToInbound() throws Exception {
        logger.info("[POM => AGENT] Method::AGTBlendedToInbound Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTBlendedToInbound");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTBlendedToOutbound() throws Exception {
        logger.info("[POM => AGENT] Method::AGTBlendedToInbound Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTBlendedToInbound");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTZoneDown(String zoneName, int gracePeriodInMins) throws Exception {
        logger.info("[POM => AGENT] Method::AGTZoneDown Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTZoneDown");
        resp.put("zoneName", zoneName);
        resp.put("gracePeriodInMins", gracePeriodInMins);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTJobAttached(String campaignName) throws Exception {
        logger.info("[POM => AGENT] Method::AGTJobAttached Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTJobAttached");
        resp.put("campaignName", campaignName);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetErrorInfoRESP(POMErrorInfo errorInfo, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetErrorInfoRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("METHOD", "AGTGetErrorInfoRESP");
        resp.put("errorInfo", errorInfo);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTDialFailedErrorInfo(POMWrapupDetails wrapupDetails, POMErrorInfo failErrorInfo, String sessionID)
            throws Exception {
        logger.info("[POM => AGENT] Method::AGTDialFailedErrorInfo Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTDialFailedErrorInfo");
        resp.put("wrapupDetails", wrapupDetails);
        resp.put("failErrorInfo", failErrorInfo);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTConsultDialFailedErrorInfo(POMErrorInfo failErrorInfo, String sessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultDialFailedErrorInfo Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTConsultDialFailedErrorInfo");
        resp.put("failErrorInfo", failErrorInfo);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTSessionIDChange(String oldSessionID, String newSessionID) throws Exception {
        logger.info("[POM => AGENT] Method::AGTSessionIDChange Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTSessionIDChange");
        resp.put("oldSessionID", oldSessionID);
        resp.put("newSessionID", newSessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTLogonRESP(int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTLogonRESP Invoked.");
        Map<String, String> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTLogonRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
        MessageObj.sendMessage(this.socketSession, respJson);
    }
    
    @Override
    public void AGTLogoffRESP(int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTLogoffRESP Invoked");
        
        Map<String, String> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTLogoffRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
        
    }
    
    @Override
    public void AGTStateChangeRESP(POMAgentState agentState, int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTStateChangeRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTStateChangeRESP");
        resp.put("agentState", agentState);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTHoldCallRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTHoldCallRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTHoldCallRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTUnholdCallRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTUnholdCallRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTUnholdCallRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTReleaseLineRESP(POMWrapupDetails wrapupDetails, String sessionID, int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTReleaseLineRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTReleaseLineRESP");
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetCompCodesRESP(POMCompletionCode[] completionCodesList, String sessionID, int result)
            throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTGetCompCodesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTGetCompCodesRESP");
        resp.put("completionCodesList", completionCodesList);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTWrapupContactRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTWrapupContactRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTWrapupContactRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTConsultCallRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConsultCallRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTConsultCallRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTCancelConsultRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCancelConsultRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTCancelConsultRESP");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTGetConsultTypesRESP(POMDestinationType[] destinationTypes, boolean allowFreeForm, String sessionID,
            int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetConsultTypesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("METHOD", "AGTGetConsultTypesRESP");
        resp.put("destinationTypes", destinationTypes);
        resp.put("allowFreeForm", allowFreeForm);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetConsultDestsForTypeRESP(POMDestinationType type, POMDestination[] destinations, String sessionID,
            int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetConsultDestsForTypeRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTGetConsultDestsForTypeRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("type", type);
        resp.put("destinations", destinations);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTCompleteTransferRESP(boolean canDispose, POMWrapupDetails wrapupDetails, String sessionID,
            int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetConsultDestsForTypeRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTGetConsultDestsForTypeRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("canDispose", canDispose);
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTStartConfRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTStartConfRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTStartConfRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTEndConfRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTEndConfRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTEndConfRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTConfChangeOwnershipRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTConfChangeOwnershipRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTConfChangeOwnershipRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTExtendWrapupRESP(POMWrapupDetails wrapupDetails, String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTExtendWrapupRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTExtendWrapupRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTRedialRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTRedialRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTRedialRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTSendDTMFRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTSendDTMFRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTSendDTMFRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetCallbackTypesRESP(POMCallbackType[] callbackTypes, String defaultExpiryTime, String sessionID,
            int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetCallbackTypesRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTGetCallbackTypesRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("callbackTypes", callbackTypes);
        resp.put("defaultExpiryTime", defaultExpiryTime);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetCallbackDestsForTypeRESP(POMCallbackType type, POMCallbackDest[] callbackDests, String sessionID,
            int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetCallbackDestsForTypeRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTGetCallbackDestsForTypeRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("type", type);
        resp.put("callbackDests", callbackDests);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTCreateCallbackRESP(String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTCreateCallbackRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTCreateCallbackRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetErrorStringRESP(String errorMsg, String localizedErrorMsg, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetErrorStringRESP Invoked.");
        
        Map<String, String> resp = new HashMap<>();
        resp.put("result", String.valueOf(result));
        resp.put("METHOD", "AGTGetErrorStringRESP");
        resp.put("localizedErrorMsg", localizedErrorMsg);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        MessageObj.sendMessage(this.socketSession, respJson);
        
    }
    
    @Override
    public void AGTPreviewDialRESP(String sessionID, int result) throws Exception {
        
        logger.info("[POM => AGENT] Method::AGTPreviewDialRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTPreviewDialRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTPreviewCancelRESP(POMWrapupDetails wrapupDetails, String sessionID, int result) throws Exception {
        logger.info("[POM => AGENT] Method::AGTPreviewDialRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTPreviewDialRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("wrapupDetails", wrapupDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
    }
    
    @Override
    public void AGTGetCustomerDetailsRESP(POMCustomerDetails customerDetails, String sessionID, int result)
            throws Exception {
        logger.info("[POM => AGENT] Method::AGTGetCustomerDetailsRESP Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTGetCustomerDetailsRESP");
        resp.put("result", result);
        resp.put("sessionID", sessionID);
        resp.put("customerDetails", customerDetails);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTAgentLoggedOut() throws Exception {
        logger.info("[POM => AGENT] Method::AGTAgentLoggedOut Invoked.");
        Map<String, Object> resp = new HashMap<>();
        resp.put("METHOD", "AGTAgentLoggedOut");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String respJson = objectMapper.writeValueAsString(resp);
        MessageObj.sendMessage(this.socketSession, respJson);
        logger.info("Send Message to session id= [" + this.socketSession.getId() + "], RESP= " + respJson);
        
    }
    
    @Override
    public void AGTGetWorkRequestIdRESP(POMContextStoreData arg0, String arg1, int arg2) throws Exception {
        // TODO
    }
    
    public String getSessionID() {
        return sessionID;
    }
    
    public static void main(String args[]) throws Exception {
        Map<String, Object> resp = new HashMap<>();
        POMWrapupDetails www = new POMWrapupDetails();
        www.setAcwExtendable(false);
        www.setAcwMaxTime(1000);
        POMCompletionCode aCode = new POMCompletionCode();
        aCode.setcodeID("111");
        aCode.setcodeValue("111value");
        www.setDefaultCompCode(aCode);
        resp.put("result", "1");
        resp.put("METHOD", "AGTLogonRESP");
        
        String[] aStrings = { "1", "2" };
        
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(aStrings));
    }
    
}
