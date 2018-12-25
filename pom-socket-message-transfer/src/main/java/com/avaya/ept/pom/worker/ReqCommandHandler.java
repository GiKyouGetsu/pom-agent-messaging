package com.avaya.ept.pom.worker;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avaya.ept.pom.agent.AgentSession;
import com.avaya.ept.pom.agent.AgentSessionManager;
import com.avaya.ept.pom.pojo.MessageObj;
import com.avaya.sdk.POMAgentFactory;
import com.avaya.sdk.Agent.POMAgent;
import com.avaya.sdk.Data.POMAgentState;
import com.avaya.sdk.Data.POMAttribute;
import com.avaya.sdk.Data.POMCallbackDest;
import com.avaya.sdk.Data.POMCallbackType;
import com.avaya.sdk.Data.POMCompletionCode;
import com.avaya.sdk.Data.POMContactNumber;
import com.avaya.sdk.Data.POMDestination;
import com.avaya.sdk.Data.POMDestinationType;
import com.avaya.sdk.Data.POMErrorCode;
import com.avaya.sdk.Data.POMKeyValuePair;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReqCommandHandler {
    private static final Logger logger = LogManager.getLogger(ReqCommandHandler.class);
    
    private final AgentSessionManager manager = AgentSessionManager.getInstance();
    
    public ReqCommandHandler() {
    }
    
    public void handleAGTLogon(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTLogon command");
        String agentId = messageObj.getParameterMap().get("agentID");
        String agentExt = messageObj.getParameterMap().get("agentExt");
        String pwd = messageObj.getParameterMap().get("pwd");
        String isForce = messageObj.getParameterMap().get("isForce");
        String locale = messageObj.getParameterMap().get("locale");
        String timeZone = messageObj.getParameterMap().get("timeZone");
        String zoneName = messageObj.getParameterMap().get("zoneName");
        String orgName = messageObj.getParameterMap().get("orgName");
        
        SDKWorker worker = new SDKWorker(agentId);
        
        try {
            POMAgent pomAgent = POMAgentFactory.getPOMAgent(agentId, worker);
            worker.setPomAgtObj(pomAgent);
            worker.setSocketSession(session);
            if (orgName != null) {
                pomAgent.AGTLogon(agentExt, pwd, Boolean.parseBoolean(isForce), locale, timeZone, zoneName, orgName);
            } else {
                pomAgent.AGTLogon(agentExt, pwd, Boolean.parseBoolean(isForce), locale, timeZone, zoneName);
            }
            
            AgentSession agentSession = new AgentSession();
            
            agentSession.setSocketSessionId(session.getId());
            agentSession.setSocketSession(session);
            agentSession.setStation(agentExt);
            agentSession.setAgentWorker(worker);
            
            this.manager.addAgentSession(agentSession);
        } catch (Exception e) {
            logger.info("AGTLogon error");
            logger.error(e);
        }
    }
    
    public void handleAGTLogout(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTLogout command");
        
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTLogoff();
            this.manager.removeAgentSession(session.getId());
            
        } catch (Exception e) {
            logger.info("AGTLogon error");
            logger.error(e);
        }
        
        logger.info("Recieved logon command");
    }
    
    public void handleAGTGetErrorString(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTGetErrorString command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTGetErrorString(Integer.parseInt(messageObj.getParameterMap().get("errorCode")));
            
        } catch (Exception e) {
            logger.info("AGTGetErrorString error");
            logger.error(e);
        }
    }
    
    public void handleAGTStateChange(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTStateChange command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            POMAgentState agentState = POMAgentState.valueOf(messageObj.getParameterMap().get("agentState"));
            String reasonCode = messageObj.getParameterMap().get("reasonCode");
            String reasonName = messageObj.getParameterMap().get("reasonName");
            String hasWalkedAway = messageObj.getParameterMap().get("hasWalkedAway");
            
            pomAgent.AGTStateChange(agentState, reasonCode, reasonName, Boolean.valueOf(hasWalkedAway));
            
        } catch (Exception e) {
            logger.info("AGTStateChange error");
            logger.error(e);
        }
    }
    
    public void handleAGTHoldCall(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTHoldCall command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTHoldCall(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTHoldCall error");
            logger.error(e);
        }
    }
    
    public void handleAGTUnHoldCall(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTUnHoldCall command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTUnholdCall(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTUnHoldCall error");
            logger.error(e);
        }
    }
    
    public void handleAGTReleaseLine(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTReleaseLine command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTReleaseLine(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTReleaseLine error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetCompCodes(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTGetCompCodes command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTGetCompCodes(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTGetCompCodes error");
            logger.error(e);
        }
    }
    
    public void handleAGTWrapupContact(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTWrapupContact command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            POMCompletionCode completionCode = objectMapper
                    .readValue(messageObj.getParameterMap().get("completionCode"), POMCompletionCode.class);
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTWrapupContact(completionCode, sessionID);
            
        } catch (Exception e) {
            logger.info("AGTWrapupContact error");
            logger.error(e);
        }
    }
    
    public void handleAGTExtendWrapup(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTExtendWrapup command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTExtendWrapup(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTExtendWrapup error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetConsultTypes(MessageObj messageObj, Session session) {
        
        logger.info("Handle AGTGetConsultTypes command.");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTGetConsultTypes(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTGetConsultTypes error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetConsultDestsForType(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetConsultDestsForType");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            POMDestinationType destinationType = POMDestinationType
                    .valueOf(messageObj.getParameterMap().get("destinationType"));
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTGetConsultDestsForType(destinationType, sessionID);
            
        } catch (Exception e) {
            logger.info("AGTGetConsultDestsForType error");
            logger.error(e);
        }
    }
    
    public void handleAGTConsultCall(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTConsultCall");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String destinationString = messageObj.getParameterMap().get("destination");
            
            ObjectMapper josnMapper = new ObjectMapper();
            POMDestination destination = josnMapper.readValue(destinationString, POMDestination.class);
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTConsultCall(destination, sessionID);
            
        } catch (Exception e) {
            logger.info("AGTGetConsultDestsForType error");
            logger.error(e);
        }
    }
    
    public void handleAGTCompleteTransfer(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTCompleteTransfer");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTCompleteTransfer(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTCompleteTransfer error");
            logger.error(e);
        }
    }
    
    public void handleAGTCancelConsult(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTCancelConsult");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            String destAgentID = messageObj.getParameterMap().get("destAgentID");
            pomAgent.AGTCancelConsult(destAgentID, sessionID);
            
        } catch (Exception e) {
            logger.info("AGTCancelConsult error");
            logger.error(e);
        }
    }
    
    public void handleAGTStartConf(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTStartConf");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTStartConf(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTStartConf error");
            logger.error(e);
        }
    }
    
    public void handleAGTEndConf(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTEndConf");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTEndConf(sessionID);
            
        } catch (Exception e) {
            logger.info("AGTEndConf error");
            logger.error(e);
        }
    }
    
    public void handleAGTConfChangeOwnership(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTConfChangeOwnership");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTConfChangeOwnership(sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTConfChangeOwnership error");
            logger.error(e);
        }
    }
    
    public void handleAGTRedial(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTRedial");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            POMContactNumber contactNumber = objectMapper.readValue(messageObj.getParameterMap().get("contactNumber"),
                    POMContactNumber.class);
            pomAgent.AGTRedial(contactNumber);
            
        } catch (Exception e) {
            logger.info("Handle AGTRedial error");
            logger.error(e);
        }
    }
    
    public void handleAGTSendDTMF(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTSendDTMF");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String dtmf = messageObj.getParameterMap().get("dtmf");
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTSendDTMF(dtmf, sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTSendDTMF error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetCallbackTypes(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetCallbackTypes");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTGetCallbackTypes(sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTGetCallbackTypes error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetCallbackTypesForTypes(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetCallbackDestsForType");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            POMCallbackType callbackType = POMCallbackType.valueOf(messageObj.getParameterMap().get("callbackType"));
            String sessionID = messageObj.getParameterMap().get("sessionID");
            String zoneName = messageObj.getParameterMap().get("zoneName");
            pomAgent.AGTGetCallbackDestsForType(callbackType, zoneName, sessionID); // TODO METHOD IS WRONG
            
        } catch (Exception e) {
            logger.info("Handle AGTGetCallbackDestsForType error");
            logger.error(e);
        }
    }
    
    public void handleAGTCreateCallback(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTCreateCallback");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            POMCallbackType callbackType = POMCallbackType.valueOf(messageObj.getParameterMap().get("callbackType"));
            POMCallbackDest callbackDest = objectMapper.readValue(messageObj.getParameterMap().get("callbackDest"),
                    POMCallbackDest.class);
            String callbackTime = messageObj.getParameterMap().get("callbackTime");
            String callbackTimezone = messageObj.getParameterMap().get("callbackTimezone");
            String callbackExpiryTime = messageObj.getParameterMap().get("callbackExpiryTime");
            POMContactNumber contactNumber = objectMapper.readValue(messageObj.getParameterMap().get("contactNumber"),
                    POMContactNumber.class);
            String agentNotes = messageObj.getParameterMap().get("agentNotes");
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTCreateCallback(callbackType, callbackDest, callbackTime, callbackTimezone, callbackExpiryTime,
                    contactNumber, agentNotes, sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTCreateCallback error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetErrorInfo(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetErrorInfo");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            POMErrorCode errorCode = objectMapper.readValue(messageObj.getParameterMap().get("errorCode"),
                    POMErrorCode.class);
            pomAgent.AGTGetErrorInfo(errorCode);
            
        } catch (Exception e) {
            logger.info("Handle AGTGetErrorInfo error");
            logger.error(e);
        }
    }
    
    public void handleAGTPreviewDial(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTPreviewDial");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            String sessionID = messageObj.getParameterMap().get("sessionID");
            POMContactNumber contactNumber = objectMapper.readValue(messageObj.getParameterMap().get("contactNumber"),
                    POMContactNumber.class);
            
            pomAgent.AGTPreviewDial(contactNumber, sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTPreviewDial error");
            logger.error(e);
        }
    }
    
    public void handleAGTPreviewCancel(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTPreviewCancel");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTPreviewCancel(sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTPreviewCancel error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetCustomerDetails(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetCustomerDetails");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTGetCustomerDetails(sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTGetCustomerDetails error");
            logger.error(e);
        }
    }
    
    public void handleAGTSetCustomerDetail(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTSetCustomerDetail");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            POMKeyValuePair pomKeyValuePair = objectMapper
                    .readValue(messageObj.getParameterMap().get("pomKeyValuePair"), POMKeyValuePair.class);
            String sessionID = messageObj.getParameterMap().get("sessionID");
            
            pomAgent.AGTSetCustomerDetail(pomKeyValuePair, sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTSetCustomerDetail error");
            logger.error(e);
        }
    }
    
    public void handleAGTBlendToInbound(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTBlendToInbound");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTBlendToInbound();
            
        } catch (Exception e) {
            logger.info("Handle AGTBlendToInbound error");
            logger.error(e);
        }
    }
    
    public void handleAGTBlendToOutbound(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTBlendToOutbound");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTBlendToOutbound();
            
        } catch (Exception e) {
            logger.info("Handle AGTBlendToOutbound error");
            logger.error(e);
        }
    }
    
    public void handleAGTNailupAgent(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTNailupAgent");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTNailupAgent();
            
        } catch (Exception e) {
            logger.info("Handle AGTNailupAgent error");
            logger.error(e);
        }
    }
    
    public void handleAGTReadyForNailup(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTReadyForNailup");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTReadyForNailup();
            
        } catch (Exception e) {
            logger.info("Handle AGTReadyForNailup error");
            logger.error(e);
        }
    }
    
    public void handleAGTLostNailing(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTLostNailing");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTLostNailing();
            
        } catch (Exception e) {
            logger.info("Handle AGTLostNailing error");
            logger.error(e);
        }
    }
    
    public void handleAGTPendingLogout(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTPendingLogout");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTPendingLogout();
            
        } catch (Exception e) {
            logger.info("Handle AGTPendingLogout error");
            logger.error(e);
        }
    }
    
    public void handleAGTAddAgentNote(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTAddAgentNote");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String agentNote = messageObj.getParameterMap().get("agentNote");
            pomAgent.AGTAddAgentNote(agentNote);
            
        } catch (Exception e) {
            logger.info("Handle AGTAddAgentNote error");
            logger.error(e);
        }
    }
    
    public void handleAGTRefreshAgentNotes(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTRefreshAgentNotes");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            pomAgent.AGTRefreshAgentNotes(); // TODO HAVE NO PARAMETERS
            
        } catch (Exception e) {
            logger.info("Handle AGTRefreshAgentNotes error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetTimezones(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetTimezones");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            pomAgent.AGTGetTimezones();
        } catch (Exception e) {
            logger.info("Handle AGTGetTimezones error");
            logger.error(e);
        }
    }
    
    public void handleAGTAvailableForNailup(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTAvailableForNailup");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            pomAgent.AGTAvailableForNailup();
        } catch (Exception e) {
            logger.info("Handle AGTAvailableForNailup error");
            logger.error(e);
        }
    }
    
    public void handleAGTAgentDisconnected(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTAgentDisconnected");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            pomAgent.AGTAgentDisconnected();
        } catch (Exception e) {
            logger.info("Handle AGTAgentDisconnected error");
            logger.error(e);
        }
    }
    
    public void handleAGTAddToDNC(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTAddToDNC");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            POMAttribute[] addressList = objectMapper.readValue(messageObj.getParameterMap().get("addressList"),
                    POMAttribute[].class);
            
            pomAgent.AGTAddToDNC(addressList);
        } catch (Exception e) {
            logger.info("Handle AGTAddToDNC error");
            logger.error(e);
        }
    }
    
    public void handleAGTIsInDNC(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTIsInDNC");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            
            String addressValue = messageObj.getParameterMap().get("addressValue");
            String sessionID = messageObj.getParameterMap().get("sessionID");
            pomAgent.AGTIsInDNC(addressValue, sessionID);
            
        } catch (Exception e) {
            logger.info("Handle AGTIsInDNC error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetZoneList(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetZoneList");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTGetZoneList();
            
        } catch (Exception e) {
            logger.info("Handle AGTGetZoneList error");
            logger.error(e);
        }
    }
    
    public void handleAGTGetContactAttributes(MessageObj messageObj, Session session) {
        
        logger.info("AGT POM Command:: AGTGetContactAttributes");
        try {
            AgentSession agentSession = this.manager.getAgentSessionBySocketSessionId(session.getId());
            POMAgent pomAgent = agentSession.getAgentWorker().getPomAgtObj();
            pomAgent.AGTGetContactAttributes();
            
        } catch (Exception e) {
            logger.info("Handle AGTGetContactAttributes error");
            logger.error(e);
        }
    }
    
    public void handleException(Session session) {
        logger.info("Session id= [" + session.getId() + "]" + " Exception, Forced to logout this agent");
        try {
            this.manager.getAgentSessionBySocketSessionId(session.getId()).getAgentWorker().getPomAgtObj().AGTLogoff();
        } catch (Exception e) {
            logger.error("Logoff agent = [" 
                    + this.manager.getAgentSessionBySocketSessionId(session.getId()).getAgentID() 
                    + "] error");
            logger.error(e);
        }
        
    }
    
    public void logffAllAgent() {
        logger.info("Server is Error, Forced to logout All agent");
        
        Iterator<Entry<String, AgentSession>> iter = AgentSessionManager.getInstance().getagentSessionMap().entrySet().iterator(); 
        while (iter.hasNext()) {
            Entry<String, AgentSession> entry = (Entry<String, AgentSession>) iter.next(); 
            AgentSession agentSession = entry.getValue();
            try {
                agentSession.getAgentWorker().getPomAgtObj().AGTLogoff();
                logger.info("Logoff agent = [" + agentSession.getAgentID() + "] ");
            } catch (Exception e) {
                logger.error("Logoff agent = [" + agentSession.getAgentID() + "] error");
                logger.error(e);
            }
        } 
    }
}
